package ch.bfh.bti7081.s2019.black.spitexorganizer.report.view.assembler;

import ch.bfh.bti7081.s2019.black.spitexorganizer.appointment.view.assembler.AppointmentAssembler;
import ch.bfh.bti7081.s2019.black.spitexorganizer.report.model.Report;
import ch.bfh.bti7081.s2019.black.spitexorganizer.report.view.dtos.ReportDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReportAssembler {

    @Autowired
    AppointmentAssembler appointmentAssembler;

    public List<ReportDto> toDtos(List<Report> reports) {
        List<ReportDto> reportDtos = new ArrayList<ReportDto>();
        for (Report report : reports) {
            reportDtos.add(toDto(report));
        }
        return reportDtos;
    }

    public ReportDto toDto(Report report) {
        if (report == null) return null;

        ReportDto reportDtos = new ReportDto();
        reportDtos.setId(report.getId());
        reportDtos.setDescription(report.getDescription());
        reportDtos.setEdit(report.getEdit());
        reportDtos.setAppointmentDto(appointmentAssembler.toReportDto(report.getAppointment()));

        return reportDtos;
    }
}
