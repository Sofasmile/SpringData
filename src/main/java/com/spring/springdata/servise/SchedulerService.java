package com.spring.springdata.servise;

import com.spring.springdata.entity.Student;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SchedulerService {

    private static final String CRON = "*/10 * * * * *";

    private final StudentService studentService;

    private final EmailService emailService;

    @Scheduled(cron = CRON)
    public void sendMailToUsers() {
        LocalDate date = LocalDate.now();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();
        List<Student> list = studentService.getStudentsByBirthday(month, day);
        if (!list.isEmpty()) {
            list.forEach(student -> {
                try {
                    String message = "Happy Birthday dear " + student.getName() + "!";
                    emailService.send(student.getEmail(), "Happy Birthday!", message);
                    log.info("Email have been sent. Student id: {}, Date: {}", student.getId(), date);
                } catch (Exception e) {
                    log.error("Email can't be sent.Student's id: {}, Error: {}", student.getId(), e.getMessage());
                    log.error("Email can't be sent", e);
                }
            });
        }
    }

}