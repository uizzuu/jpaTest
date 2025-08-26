package com.my.jpaTest.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IdolMember {
    @Id
    private String m_id;
    private String m_name;

    @ManyToOne
    @JoinColumn(name = "g_id")
    private Girlgroup girlgroup;
}
