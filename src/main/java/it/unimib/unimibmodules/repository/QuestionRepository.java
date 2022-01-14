package it.unimib.unimibmodules.repository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import it.unimib.unimibmodules.dao.QuestionDAO;
import it.unimib.unimibmodules.model.Question;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import it.unimib.unimibmodules.exception.NotFoundException;

/**
 * Repository for the Question class.
 * @author Khalil
 * @version 0.1.0
 */
@Component("questionRepository")
public class QuestionRepository implements Repository <Question> {
	
	/**
     * The instance of questionDAO that will be used to perform actions to the DB
     */
	private final QuestionDAO questionDAO;

	@Autowired
	public QuestionRepository(QuestionDAO questionDAO) {
		this.questionDAO = questionDAO;
	}
	
	
	/**
     * Inserts an instance of Question in the database
     * @param   entity  an instance of Question
     * @see Repository#add
     */
	@Override
	public void add(Question entity) {
		questionDAO.save(entity);
	}
	
	
	/**
     * Inserts a list of questions in the database
     * @param   entities  a list of Questions
     * @see Repository#addAll
     */
	@Override
	public void addAll(List<Question> entities) {
		questionDAO.saveAll(entities);
	}
	
	
	/**
     * Finds the question identified by id in the database
     * @param   id  the id of the question to be found
     * @return      an instance of Question if there is a question identified by id, null otherwise
	 * @throws NotFoundException	if no question identified by <code>id</code> has been found
     * @see Repository#get(int id)
     */
	@Override
	public Question get(int id) throws NotFoundException{
		Optional<Question> question = questionDAO.findById(id);

		try {
			return question.orElseThrow();
		}catch (NoSuchElementException e){
			throw new NotFoundException("No Question with id " + id + " was found.");
		}
 	}
	
	
    /**
     * Returns all questions in the database.
     * @see Repository#getAll()
     * @return  a list of Questions
     */
	@Override
	public Iterable<Question> getAll() {
		return questionDAO.findAll();
	}
	
	
	/**
     * Deletes from the database the question identified by id.
     * @param   id  the id of the question to be deleted
	 * @throws  NotFoundException	if no question identified by <code>id</code> has been found
     * @see Repository#remove(int id)
     */
	@Override
	public void remove(int id) throws NotFoundException{

		try {
			questionDAO.deleteById(id);
		}catch (EmptyResultDataAccessException e){
			throw new NotFoundException("No Question with id " + id + " was found.");
		}
	}
	
	
	 /**
     * Deletes all questions in the database.
     * @see Repository#removeAll()
     */
	@Override
	public void removeAll() {
		questionDAO.deleteAll();
	}
	
	
	 /**
     * Updates a question in the database using a new instance of Question.
     * @param   entity  the new instance of Question
     * @see Repository#modify
     */
	@Override
	public void modify(Question entity) {
		questionDAO.save(entity);
	}
	
	
	
}
