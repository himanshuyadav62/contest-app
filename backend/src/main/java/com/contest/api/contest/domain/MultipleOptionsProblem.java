    package com.contest.api.contest.domain;

    import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
    import jakarta.persistence.EnumType;
    import jakarta.persistence.Enumerated;
    import jakarta.persistence.GeneratedValue;
    import jakarta.persistence.GenerationType;
    import jakarta.persistence.Id;
    import jakarta.persistence.JoinColumn;
    import jakarta.persistence.ManyToOne;
    import jakarta.persistence.OneToMany;
    import jakarta.persistence.Table;
    import lombok.Data;

    @Entity
    @Table(name = "multiple_options_problems")
    @Data
    public class MultipleOptionsProblem {

        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        @JoinColumn(name = "mop_id")
        private String mopId; // multiple options problem id

        @Enumerated(EnumType.STRING)
        private MOPType mopType;

        @OneToMany(cascade = CascadeType.ALL)
        @JoinColumn(name = "mop_id")
        private List<Option> options;

        @ManyToOne
        @JoinColumn(name = "contest_id")
        private Contest contest;

        @ManyToOne
        @JoinColumn(name = "problem_id")
        @OnDelete(action = OnDeleteAction.CASCADE) 
        private Problem problem;
    }
