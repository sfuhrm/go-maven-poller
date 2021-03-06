package com.oneandone.go.plugin.maven.client;

import com.oneandone.go.plugin.maven.util.MavenRevision;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.Assert.*;

public class RepositoryResponseHandlerTest {

    @Test
    public void testCanHandle() throws Exception {
        final InputStream stream = RepositoryResponseHandlerTest.class.getClassLoader().getResourceAsStream("web/mysql/mysql-connector-java/maven-metadata.xml");
        final StringWriter writer = new StringWriter();

        IOUtils.copy(stream, writer, StandardCharsets.UTF_8);
        final String metadata = writer.toString();

        final RepositoryResponse mockedResponse = new RepositoryResponse(metadata);
        final RepositoryResponseHandler repositoryResponseHandler = new RepositoryResponseHandler(mockedResponse);
        assertTrue(repositoryResponseHandler.canHandle());
    }

    @Test
    public void testCannotHandle() {
        final RepositoryResponseHandler repositoryResponseHandler = new RepositoryResponseHandler(new RepositoryResponse("foobar"));
        assertFalse(repositoryResponseHandler.canHandle());
    }

    @Test
    public void testGetAllVersions() throws Exception {
        final InputStream stream = RepositoryResponseHandlerTest.class.getClassLoader().getResourceAsStream("web/mysql/mysql-connector-java/maven-metadata.xml");
        final StringWriter writer = new StringWriter();

        IOUtils.copy(stream, writer, Charset.defaultCharset());
        final String metadata = writer.toString();

        final RepositoryResponse mockedResponse = new RepositoryResponse(metadata);
        final RepositoryResponseHandler repositoryResponseHandler = new RepositoryResponseHandler(mockedResponse);

        final List<MavenRevision> allVersions = repositoryResponseHandler.getAllVersions();
        assertEquals (33, allVersions.size());
    }
}