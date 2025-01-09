package com.contest.api.contest.domain;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "problems")
@Data
public class MultipleOptionsProblem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JoinColumn(name = "mop_id")
    private String mopId; // multiple options problem id

    @Enumerated(EnumType.STRING)
    private MOPType mopType;

    @OneToMany
    @JoinColumn(name = "mop_id")
    private List<Option> options;
}
