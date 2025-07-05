package net.engineeringdigest.journalApp.entity;


import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;


@Document (collection =  "config_journal_app")
@Data

@NoArgsConstructor
@AllArgsConstructor
public class ConfigJournalAppEntity {

    private String Key;
    private String Value;

}
