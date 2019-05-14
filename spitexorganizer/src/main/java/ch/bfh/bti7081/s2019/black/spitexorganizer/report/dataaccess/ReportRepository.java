package ch.bfh.bti7081.s2019.black.spitexorganizer.report.dataaccess;

import ch.bfh.bti7081.s2019.black.spitexorganizer.report.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    @Query(nativeQuery = true,
            value = "Select * from REPORT where ID in (SELECT a.REPORT_ID FROM APPOINTMENT AS a WHERE a.PATIENT_ID = ?)")
    List<Report> findByPatientId(long id);
}
