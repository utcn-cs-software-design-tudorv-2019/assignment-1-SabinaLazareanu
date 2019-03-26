package assigment1.tucn.cs.DAL.repository;

import java.util.List;

import assigment1.tucn.cs.BLL.utils.ETables;
import assigment1.tucn.cs.DAL.ExecutionException;

public interface IRepository {
	List<?> findAll(ETables table) throws ExecutionException;

	Object getById(Long id,ETables table) throws ExecutionException;

	void delete(Long id, ETables table) throws ExecutionException;
	
	Object getObjectWithMaxID(ETables table) throws ExecutionException;

}
