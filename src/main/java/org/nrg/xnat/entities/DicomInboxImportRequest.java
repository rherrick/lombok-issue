package org.nrg.xnat.entities;

import lombok.*;

import javax.persistence.Entity;
import java.util.Map;

@Entity
@Data
@Builder
@NoArgsConstructor
// @AllArgsConstructor
public class DicomInboxImportRequest {
    @NonNull
    private String username;

    @NonNull
    private Map<String, String> parameters;

    @NonNull
    private String sessionPath;
}