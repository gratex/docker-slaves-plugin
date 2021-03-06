package it.dockins.dockerslaves.spi;

import java.io.Closeable;
import java.io.IOException;

import org.acegisecurity.context.SecurityContext;
import org.acegisecurity.context.SecurityContextHolder;
import org.jenkinsci.plugins.docker.commons.credentials.DockerServerEndpoint;
import org.jenkinsci.plugins.docker.commons.credentials.KeyMaterial;

import hudson.EnvVars;
import hudson.FilePath;
import hudson.model.Item;
import hudson.security.ACL;

/**
 * Configuration options used to access a specific (maybe dedicated to a build) Docker Host.
 * <p>
 * Intent here is to allow some infrastructure plugin to prepare a dedicated Docker Host per build,
 * using some higher level isolation, so the build is safe to do whatever it needs with it's docker
 * daemon without risk to impact other builds.
 *
 * @author <a href="mailto:nicolas.deloof@gmail.com">Nicolas De Loof</a>
 */
public class DockerHostConfig implements Closeable {

    /** Docker Host's daemon endpoint */
    private final DockerServerEndpoint endpoint;

    /** Docker API access keys  */
    private final KeyMaterial keys;

    public DockerHostConfig(DockerServerEndpoint endpoint, Item context) throws IOException, InterruptedException {
        this.endpoint = endpoint;
        final SecurityContext impersonate = ACL.impersonate(ACL.SYSTEM);
        try {
            keys = endpoint.newKeyMaterialFactory(context, FilePath.localChannel).materialize();
        } finally {
            SecurityContextHolder.setContext(impersonate);
        }
    }

    public DockerServerEndpoint getEndpoint() {
        return endpoint;
    }

    public EnvVars getEnvironment() {
        return keys.env();
    }

    @Override
    public void close() throws IOException {
        keys.close();
    }
}
