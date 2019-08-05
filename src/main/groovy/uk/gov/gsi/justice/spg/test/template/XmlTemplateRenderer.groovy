package uk.gov.gsi.justice.spg.test.template

import groovy.text.Template
import groovy.text.XmlTemplateEngine
import groovy.transform.CompileStatic
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@CompileStatic
class XmlTemplateRenderer {
    private final static Logger LOGGER = LoggerFactory.getLogger(XmlTemplateRenderer)
    private final XmlTemplateEngine engine = new XmlTemplateEngine()

    String render(final URL resource, final Map<String, Object> data) {
        final Template template = engine.createTemplate(resource)

        final writable = template.make(data)

        final StringWriter result = new StringWriter()
        writable.writeTo(result)

        LOGGER.debug("Rendered content::: {}{}", System.lineSeparator(),result)

        return removeWindowsReturnCharacter(result.toString())
    }

    @SuppressWarnings("GrMethodMayBeStatic")
    private String removeWindowsReturnCharacter(String message) {
        return message.replaceAll("\\r\\n", "\n")
    }
}
