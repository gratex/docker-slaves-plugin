package it.dockins.dockerslaves.spi;

import java.io.IOException;

import hudson.ExtensionPoint;
import hudson.model.AbstractDescribableImpl;
import hudson.model.Job;

public abstract class DockerDriverFactory extends AbstractDescribableImpl<DockerDriverFactory> implements ExtensionPoint {

    public abstract DockerDriver forJob(Job context) throws IOException, InterruptedException;

}
