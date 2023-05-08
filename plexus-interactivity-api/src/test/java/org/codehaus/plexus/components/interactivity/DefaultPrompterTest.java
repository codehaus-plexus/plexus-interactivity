package org.codehaus.plexus.components.interactivity;

/*
 * The MIT License
 *
 * Copyright (c) 2005, The Codehaus
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do
 * so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DefaultPrompterTest
{
    @Test
    void promptSimple() throws PrompterException {
        final InMemoryOutput out = new InMemoryOutput();
        final Prompter prompter = new DefaultPrompter( out, new InMemoryInput( "ok" ) );
        prompter.prompt( "test" );
        assertEquals( "test: ", out.builder.toString() );
    }

    @Test
    void promptOption() throws PrompterException {
        final InMemoryOutput out = new InMemoryOutput();
        final Prompter prompter = new DefaultPrompter( out, new InMemoryInput( "ok" ) );
        prompter.prompt( "test", "value" );
        assertEquals("test value: ", out.builder.toString());
    }

    @Test
    void promptOptions() throws PrompterException {
        final InMemoryOutput out = new InMemoryOutput();
        final Prompter prompter = new DefaultPrompter( out, new InMemoryInput( "yes" ) );
        prompter.prompt( "test", asList( "yes", "no" ), "value" );
        assertEquals("test (yes/no) value: ", out.builder.toString());
    }

    private static class InMemoryInput implements InputHandler
    {
        private final String line;

        private InMemoryInput( String line )
        {
            this.line = requireNonNull(line);
        }

        @Override
        public String readLine() {
            return line;
        }

        @Override
        public String readPassword()
        {
            throw new UnsupportedOperationException();
        }

        @Override
        public List<String> readMultipleLines()
        {
            throw new UnsupportedOperationException();
        }
    }

    private static class InMemoryOutput implements OutputHandler
    {
        private final StringBuilder builder = new StringBuilder();

        @Override
        public void write( String line )
        {
            builder.append( line );
        }

        @Override
        public void writeLine( String line )
        {
            builder.append( line );
        }
    }
}
