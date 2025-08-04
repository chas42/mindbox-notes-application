package  com.mindbox.notes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "note")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    @Column(length = 10000)
    private String content;

    @ElementCollection
    private List<String> tags;

    private String userId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Version
    private Integer version;
}