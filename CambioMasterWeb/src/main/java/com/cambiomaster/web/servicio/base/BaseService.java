package com.cambiomaster.web.servicio.base;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public abstract class BaseService<T, ID, R extends JpaRepository<T, ID>> implements IBaseService<T, ID> {

	protected R repositorio;

	public BaseService(R repo) {

		this.repositorio = repo;

	}

	@Override
	public T save(T t) {
		// TODO Auto-generated method stub
		return repositorio.save(t);
	}

	@Override
	public T findById(ID id) {
		// TODO Auto-generated method stub
		return repositorio.findById(id).orElse(null);
	}

	@Override
	public List<T> findAll() {
		// TODO Auto-generated method stub
		return repositorio.findAll();
	}

	@Override
	public T edit(T t) {
		// TODO Auto-generated method stub
		return repositorio.save(t);
	}

	@Override
	public void delete(T t) {
		// TODO Auto-generated method stub
		repositorio.delete(t);

	}

	@Override
	public void deleteById(ID id) {
		// TODO Auto-generated method stub
		repositorio.deleteById(id);
	}

}