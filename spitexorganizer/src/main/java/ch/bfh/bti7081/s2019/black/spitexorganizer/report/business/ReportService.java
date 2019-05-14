package ch.bfh.bti7081.s2019.black.spitexorganizer.report.business;

import ch.bfh.bti7081.s2019.black.spitexorganizer.report.dataaccess.ReportRepository;
import ch.bfh.bti7081.s2019.black.spitexorganizer.report.view.assembler.ReportAssembler;
import ch.bfh.bti7081.s2019.black.spitexorganizer.report.view.dtos.ReportDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {
    @Autowired
    ReportRepository reportRepository;

    @Autowired
    ReportAssembler reportAssembler;

    public List<ReportDto> findByPatientId(long id) {
        return reportAssembler.toDtos(reportRepository.findByPatientId(id));
    }
}

