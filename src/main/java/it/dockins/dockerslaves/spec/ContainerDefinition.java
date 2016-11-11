/*
 * The MIT License
 *
 *  Copyright (c) 2015, CloudBees, Inc.
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 *
 */

package it.dockins.dockerslaves.spec;

import java.io.IOException;
import java.util.List;

import org.kohsuke.stapler.DataBoundSetter;

import hudson.EnvVars;
import hudson.FilePath;
import hudson.model.AbstractDescribableImpl;
import hudson.model.TaskListener;
import it.dockins.dockerslaves.spi.DockerDriver;

/**
 * @author <a href="mailto:nicolas.deloof@gmail.com">Nicolas De Loof</a>
 */
public abstract class ContainerDefinition extends AbstractDescribableImpl<ContainerDefinition> {

    private List<Hint> hints;

    public List<Hint> getHints() {
        return hints;
    }

    @DataBoundSetter
    public void setHints(List<Hint> hints) {
        this.hints = hints;
    }

    public abstract String getImage(DockerDriver driver, FilePath workspace, TaskListener listener) throws IOException, InterruptedException;

    public void setupEnvironment(EnvVars env) {}

}
