/*
 * ProActive Parallel Suite(TM):
 * The Open Source library for parallel and distributed
 * Workflows & Scheduling, Orchestration, Cloud Automation
 * and Big Data Analysis on Enterprise Grids & Clouds.
 *
 * Copyright (c) 2007 - 2017 ActiveEon
 * Contact: contact@activeeon.com
 *
 * This library is free software: you can redistribute it and/or
 * modify it under the terms of the GNU Affero General Public License
 * as published by the Free Software Foundation: version 3 of
 * the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 * If needed, contact us to obtain a release under GPL Version 2 or 3
 * or a different license than the AGPL.
 */
package jsr223.cpython;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;

import jsr223.cpython.utils.PythonVersionGetter;


/**
 * @author ActiveEon Team
 * @since 04/10/2017
 */
public class PythonScriptEngineFactory implements ScriptEngineFactory {
    static final Map<String, String> PARAMETERS = new HashMap<>();

    static {
        String pythonEngineVersion = new PythonVersionGetter().getPythonVersion("python");
        PARAMETERS.put(ScriptEngine.NAME, "cpython");
        PARAMETERS.put(ScriptEngine.ENGINE, "cpython");
        PARAMETERS.put(ScriptEngine.ENGINE_VERSION, pythonEngineVersion);
        PARAMETERS.put(ScriptEngine.LANGUAGE, "cpython");
        PARAMETERS.put(ScriptEngine.LANGUAGE_VERSION, pythonEngineVersion);
    }

    @Override
    public String getEngineName() {
        return PARAMETERS.get(ScriptEngine.NAME);
    }

    @Override
    public String getEngineVersion() {
        return PARAMETERS.get(ScriptEngine.ENGINE_VERSION);
    }

    @Override
    public List<String> getExtensions() {
        return Arrays.asList("py", "cpy");
    }

    @Override
    public List<String> getMimeTypes() {
        return Collections.singletonList("application/python");
    }

    @Override
    public List<String> getNames() {
        return Arrays.asList(PARAMETERS.get(ScriptEngine.NAME), "cpython", "CPython");
    }

    @Override
    public String getLanguageName() {
        return PARAMETERS.get(ScriptEngine.LANGUAGE);
    }

    @Override
    public String getLanguageVersion() {
        return PARAMETERS.get(ScriptEngine.LANGUAGE_VERSION);
    }

    @Override
    public Object getParameter(String key) {
        return PARAMETERS.get(key);
    }

    @Override
    public String getMethodCallSyntax(String obj, String m, String... args) {
        String methodCall = m + " ";
        for (String arg : args) {
            methodCall += arg + " ";
        }
        return methodCall;
    }

    @Override
    public String getOutputStatement(String toDisplay) {
        return toDisplay;
    }

    @Override
    public String getProgram(String... statements) {
        return statements[0];
    }

    @Override
    public ScriptEngine getScriptEngine() {
        return new PythonScriptEngine();
    }
}
