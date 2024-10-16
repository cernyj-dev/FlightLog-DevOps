package eu.profinit.education.flightlog.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import eu.profinit.education.flightlog.AbstractIntegrationTest;
import eu.profinit.education.flightlog.to.FileExportTo;
import eu.profinit.education.flightlog.util.FileUtils;

public class CsvExportServiceTest extends AbstractIntegrationTest {

    @Autowired
    private CsvExportService testSubject;

    @Test
    public void testCSVExport() throws IOException, URISyntaxException {
        FileExportTo fileExportTo = testSubject.getAllFlightsAsCsv();


        String actualResult = new String(fileExportTo.getContent(), fileExportTo.getEncoding()).trim();
        String expectedResult = FileUtils.normalizeLineEndingsToCrLf(FileUtils.readResourceFileToString("expectedExport.csv")).trim();

        assertEquals(expectedResult, actualResult);
    }


}