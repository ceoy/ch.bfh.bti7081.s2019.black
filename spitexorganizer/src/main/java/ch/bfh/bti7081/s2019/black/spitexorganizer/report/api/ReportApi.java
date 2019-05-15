package ch.bfh.bti7081.s2019.black.spitexorganizer.report.api;

import ch.bfh.bti7081.s2019.black.spitexorganizer.report.business.ReportService;
import ch.bfh.bti7081.s2019.black.spitexorganizer.report.view.dtos.ReportDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReportApi {

    @Autowired
    ReportService reportService;

    public List<ReportDto> findByPatientId(long id) {
        return reportService.findByPatientId(id);
    }

    public ReportDto findByAppointmentId(long id) {
        return reportService.findByAppointmentId(id);
    }

    public void update(ReportDto reportDto) {
        reportService.update(reportDto);
    }
}
