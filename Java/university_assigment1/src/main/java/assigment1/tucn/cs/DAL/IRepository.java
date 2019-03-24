package assigment1.tucn.cs.DAL;

import java.util.List;

import assigment1.tucn.cs.BLL.utils.ETables;

public interface IRepository {
	List<?> findAll(ETables table) throws ExecutionException;

	Object getById(Long id,ETables table) throws ExecutionException;

	void delete(Long id, ETables table) throws ExecutionException;

}
