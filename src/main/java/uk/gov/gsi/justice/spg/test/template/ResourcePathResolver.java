package uk.gov.gsi.justice.spg.test.template;

import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class ResourcePathResolver {
    public String getEnvironmentProperty(String name) {
        return Optional.ofNullable(System.getProperty(name))
                .orElseGet(() -> System.getenv(name));
    }

    public Path getPath(String fileLocation) {
        final String scheme = "file:";
        return Paths.get(URI.create(scheme + fileLocation));
    }
}