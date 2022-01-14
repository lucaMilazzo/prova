package it.unimib.unimibmodules.model;

import it.unimib.unimibmodules.exception.EmptyFieldException;

import javax.persistence.*;
import java.util.Set;

/**
 * Represents an open-ended answer.
 * @author Davide Costantini
 * @version 0.1.0
 */
@Entity
@Table(name = "answer")
public class Answer {

    /**
     * The id of the answer.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    /**
     * The text of the answer.
     */
    private String text;

    /**
     * The user who created the answer.
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * The survey to which this answer belongs.
     */
    @ManyToOne
    @JoinColumn(name = "survey_id", nullable = false)
    private Survey survey;

    /**
     * The question to which this answer belongs.
     */
    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    /**
     * The list of close-ended answers related to this answer.
     */
    @ManyToMany
    @JoinTable(
            name = "answer_closeendedanswer",
            joinColumns = @JoinColumn(name = "answer_id"),
            inverseJoinColumns = @JoinColumn(name = "closeendedanswer_id"))
    private Set<CloseEndedAnswer> closeEndedAnswers;

    /**
     * Creates an empty answer.
     * @see it.unimib.unimibmodules.factory.AnswerFactory#createAnswer
     */
    public Answer() {

        // Empty constructor; use Answerfactory.createAnswer.
    }

    /**
     * Returns the id of the answer.
     * @return  the id of the answer
     */
    public int getId() {

        return id;
    }

    /**
     * Modifies the id of the answer, setting <code>id</code> as the new value.
     * @param   id  the new id value
     */
    public void setId(int id) {

        this.id = id;
    }

    /**
     * Returns the text of the answer.
     * @return  the text of the answer
     */
    public String getText() {

        return text;
    }

    /**
     * Modifies the text of the answer, setting <code>text</code> as the new value.
     * @param   text                the new text value
     * @throws  EmptyFieldException if the answer is empty
     */
    public void setText(String text) throws EmptyFieldException {

        if ((closeEndedAnswers != null && closeEndedAnswers.isEmpty()) || text == null || text.isBlank())
            throw new EmptyFieldException("Answers must not be empty.");
        this.text = text;
    }
    
    /**
     * Returns the user who created the answer.
     * @return    an instance of User containing the user who created the answer
     */
    public User getUser() {

        return user;
    }

    /**
     * Modifies the user who created the answer, setting <code>user</code> as the new user.
     * @param   user    the new user
     */
    public void setUser(User user) {

        this.user = user;
    }

    public Survey getSurvey() {

        return survey;
    }

    public void setSurvey(Survey survey) {

        this.survey = survey;
    }

    public Question getQuestion() {

        return question;
    }

    public void setQuestion(Question question) {

        this.question = question;
    }

    public Set<CloseEndedAnswer> getCloseEndedAnswers() {

        return closeEndedAnswers;
    }

    public void setCloseEndedAnswers(Set<CloseEndedAnswer> closeEndedAnswers) {

        this.closeEndedAnswers = closeEndedAnswers;
    }
}
