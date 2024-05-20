package t.education.metricsproducer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import t.education.metricsproducer.dto.request.ExamRq;
import t.education.metricsproducer.dto.responce.SpecialityRs;
import t.education.metricsproducer.entity.ExamGroup;
import t.education.metricsproducer.entity.Speciality;
import t.education.metricsproducer.exception.NotFoundException;
import t.education.metricsproducer.exception.ExamException;
import t.education.metricsproducer.mapper.SpecialityMapper;
import t.education.metricsproducer.repository.ExamRepository;
import t.education.metricsproducer.repository.SpecialityRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExamCalculatorService {

    private final SpecialityRepository specialityRepository;
    private final ExamRepository examRepository;


    public List<SpecialityRs> getSpeciality(ExamRq examRq) {
        List<ExamGroup> examGroups = getExamGroups(examRq);
        List<Speciality> specialityList = new ArrayList<>();
        for (ExamGroup group : examGroups) {
            specialityList.addAll(specialityRepository.findByExamGroup(group));
        }
        return specialityList.stream().map(SpecialityMapper.INSTANCE::toDto).collect(Collectors.toList());

    }

    public List<ExamGroup> getExamGroups(ExamRq examRq) {
        List<ExamGroup> examGroups = new ArrayList<>();

        if (isCorrectPoints("russianLanguage", examRq.getRussianLanguage()) &&
                isCorrectPoints("mathematics", examRq.getMathematics()) &&
                isCorrectPoints("biology", examRq.getBiology())) {
            examGroups.add(ExamGroup.rus_math_bio);
        }
        if (isCorrectPoints("russianLanguage", examRq.getRussianLanguage()) &&
                isCorrectPoints("mathematics", examRq.getMathematics()) &&
                isCorrectPoints("informatics", examRq.getInformatics())) {
            examGroups.add(ExamGroup.rus_math_inf);
        }
        if (isCorrectPoints("russianLanguage", examRq.getRussianLanguage()) &&
                isCorrectPoints("history", examRq.getHistory()) &&
                isCorrectPoints("socialScience", examRq.getSocialScience())) {
            examGroups.add(ExamGroup.rus_hist_social);
        }
        if (examGroups.isEmpty()) {
            throw new ExamException();
        }
        return examGroups;
    }

    public boolean isCorrectPoints(String examTitle, Integer examPoints) {
        if (examPoints == null) {
            return false;
        } else return examPoints >= examRepository.findByTitle(examTitle).orElseThrow(() ->
                new NotFoundException(examTitle)).getMinPoint();
    }
}
