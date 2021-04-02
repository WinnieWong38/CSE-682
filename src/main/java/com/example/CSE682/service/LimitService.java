package com.example.CSE682.service;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CSE682.repository.LimitRepository;
import com.example.CSE682.model.Limit;
import com.example.CSE682.model.Category;

@Service
public class LimitService implements ILimitService {

	@Autowired
	LimitRepository limitRepository;

	@Autowired
	IExpenseService expenseService;

	@Autowired
	ICategoryService categoryService;

	@Autowired
	IUserService userService;

	@Override
	/**
	 * @see com.example.CSE682.service.ILimitService#getAll()
	 */
	public List<Limit> getAll() {
		return limitRepository.findAllByUser(userService.getLoggedinUser());
	}

	@Override
	/**
	 * @see com.example.CSE682.service.ILimitService#getLimitById(Long)
	 */
	public Limit getLimitById(Long id) {
		return limitRepository.getLimitById(id);
	}

	@Override
	/**
	 * @see com.example.CSE682.service.ILimitService#save(Limit)
	 */
	public Limit save(Limit limit) {
		limit.setUser(userService.getLoggedinUser());
		return limitRepository.save(limit);
	}

	@Override
	/**
	 * @see com.example.CSE682.service.ILimitService#edit(Limit, Long)
	 */
	public Limit edit(Limit newLimit, Long id) {
		Limit limit = limitRepository.getLimitByIdByCategory(newLimit.getCategory(), userService.getLoggedinUser());
		if (limit == null) {
			newLimit.setUser(userService.getLoggedinUser());
			return limitRepository.save(newLimit);
		}
		if (newLimit.getLimit() < 0.0) {
			limitRepository.deleteById(limit.getLimitId());
		} else {
			limit.setLimit(newLimit.getLimit());
			limit.setCategory(newLimit.getCategory());
			return limitRepository.save(limit);
		}
		return null;
	}

	@Override
	/**
	 * @see com.example.CSE682.service.ILimitService#delete(Long)
	 */
	public void delete(Long id) {
		limitRepository.deleteById(id);
	}

	@Override
	/**
	 * @see com.example.CSE682.service.ILimitService#limitBarChar()
	 */
	public ArrayList<ArrayList<Object>> limitBarChar() {
		ArrayList<ArrayList<Object>> returnList = new ArrayList<>();
		ArrayList<Object> categories = new ArrayList<>();
		ArrayList<Object> expenses = new ArrayList<>();
		ArrayList<Object> limits = new ArrayList<>();

		for (Limit limit : limitRepository.findAllByUser(userService.getLoggedinUser())) {
			categories.add(limit.getCategory().getCategory());
			limits.add(limit.getLimit());
			expenses.add(expenseService.getTotalCostCurrentMonthByCategoryId(limit.getCategory().getCategoryid()));
		}

		returnList.add(limits);
		returnList.add(expenses);
		returnList.add(categories);
		return returnList;
	}

	@Override
	/**
	 * @see com.example.CSE682.service.ILimitService#getLimitByIdByCategory(Long)
	 */
	public Limit getLimitByIdByCategory(Long categoryId) {
		Category category = categoryService.getCategoryById(categoryId);
		return limitRepository.getLimitByIdByCategory(category, userService.getLoggedinUser());
	}

	@Override
	/**
	 * @see com.example.CSE682.service.ILimitService#setTotalLimit(Limit)
	 */
	public Limit setTotalLimit(Limit totalLimit) {
		Limit total_limit_repository = limitRepository.getTotalLimit(userService.getLoggedinUser());
		if (total_limit_repository == null) {
			totalLimit.setUser(userService.getLoggedinUser());
			total_limit_repository = limitRepository.save(totalLimit);
		} else {
			total_limit_repository.setLimit(totalLimit.getLimit());
			total_limit_repository = limitRepository.save(total_limit_repository);
		}

		return total_limit_repository;
	}

	@Override
	/**
	 * @see com.example.CSE682.service.ILimitService#getTotalLimit()
	 */
	public Limit getTotalLimit() {
		return limitRepository.getTotalLimit(userService.getLoggedinUser());
	}

	@Override
	/**
	 * @see com.example.CSE682.service.ILimitService#deleteByCategory(Long)
	 */
	public void deleteByCategory(Long id) {
		Limit limit = getLimitByIdByCategory(id);
		limitRepository.deleteById(limit.getLimitId());
	}
}
