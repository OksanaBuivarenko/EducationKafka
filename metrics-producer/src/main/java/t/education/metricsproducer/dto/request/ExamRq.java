package t.education.metricsproducer.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExamRq {
    private Integer russianLanguage;
    private Integer mathematics;
    private Integer socialScience;
    private Integer foreignLanguage;
    private Integer informatics;
    private Integer biology;
    private Integer geography;
    private Integer chemistry;
    private Integer physics;
    private Integer literature;
    private Integer history;
}
