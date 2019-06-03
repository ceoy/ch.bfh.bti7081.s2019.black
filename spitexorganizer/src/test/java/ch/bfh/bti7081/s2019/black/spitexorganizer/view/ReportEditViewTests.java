package ch.bfh.bti7081.s2019.black.spitexorganizer.view;

import ch.bfh.bti7081.s2019.black.spitexorganizer.report.api.ReportApi;
import ch.bfh.bti7081.s2019.black.spitexorganizer.report.view.dtos.ReportDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@DirtiesContext
public class ReportEditViewTests {

    @Autowired
    private ReportApi reportApi;

    @Test
    public void reportNotFound() {
        ReportDto reportDto = reportApi.findByAppointmentId(123);
        assertNull(reportDto);
    }
}
