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

import java.util.List;

/**
 * Prompt the user for input.
 *
 * @author <a href="mailto:brett@apache.org">Brett Porter</a>
 */
public interface Prompter {
    /**
     * Ask a question and return the answer.
     *
     * @param message the question
     * @return the answer, or {@code null} at the end of the input
     */
    String prompt(String message) throws PrompterException;

    /**
     * Ask a question that has a default answer, which is shown after the question.
     *
     * @param message the question
     * @param defaultReply the answer used when the user gives an empty one
     * @return the answer, or {@code defaultReply} for an empty answer
     */
    String prompt(String message, String defaultReply) throws PrompterException;

    /**
     * Ask a question that only accepts one of the given answers. The prompt repeats after
     * "Invalid selection." until the answer is in the list.
     *
     * @param message the question
     * @param possibleValues the accepted answers, shown after the question
     * @return one of {@code possibleValues}
     * @throws PrompterException when the input ends before a valid answer was given
     */
    String prompt(String message, List<String> possibleValues) throws PrompterException;

    /**
     * Ask a question that only accepts one of the given answers and has a default.
     *
     * @param message the question
     * @param possibleValues the accepted answers, shown after the question
     * @param defaultReply the answer used when the user gives an empty one
     * @return one of {@code possibleValues}
     */
    String prompt(String message, List<String> possibleValues, String defaultReply) throws PrompterException;

    /**
     * Ask for a password. Whether the typed characters are echoed depends on the
     * {@link InputHandler}: the default one echoes them, the {@code jline} one masks them.
     *
     * @param message the question
     * @return the password
     */
    String promptForPassword(String message) throws PrompterException;

    /**
     * Show a message to the user without asking for input.
     *
     * @param message the message
     */
    void showMessage(String message) throws PrompterException;
}
