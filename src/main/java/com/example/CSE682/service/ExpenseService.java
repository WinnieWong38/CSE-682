package com.example.CSE682.service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CSE682.model.Category;
import com.example.CSE682.model.Expense;
import com.example.CSE682.model.Limit;
import com.example.CSE682.repository.CategoryRepository;
import com.example.CSE682.repository.ExpenseRepository;
import com.example.CSE682.repository.LimitRepository;

@Service
public class ExpenseService implements IExpenseService{

	@Autowired
	ExpenseRepository expenseRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	LimitRepository limitRepository;
	
	@Autowired
	IUserService userService;
	
	@Override
    public List<Expense> getAll() {
        return expenseRepository.findAllByUser(userService.getLoggedinUser());
    }
	
	@Override
	public Expense getExpenseById(Long id) {
		return expenseRepository.getExpenseById(id);
	}
	
	@Override 
	public Expense save(Expense expense) {
		return expenseRepository.save(expense);
	}
	
	@Override
	public Expense edit(Expense newExpense, Long id) {
		Expense expense = expenseRepository.getExpenseById(id);
		expense.setCategory(newExpense.getCategory());
		expense.setCost(newExpense.getCost());
		expense.setExpense(newExpense.getExpense());
		expense.setDate(newExpense.getDate());
		expense.setIsPaid(newExpense.getIsPaid());
		return expenseRepository.saveAndFlush(expense);
	}
	
	@Override 
	public void deleteById(Long id) {
		expenseRepository.deleteById(id);
	}
	
	@Override
	public double getTotalCost() {
		return expenseRepository.getTotalCost(userService.getLoggedinUser());
	}
	
	@Override
	public double getTotalCostByCategory(Long id) {
		Optional<Category> category = categoryRepository.findById(id);
		if(category.isEmpty()) return 0.0;
		int count = expenseRepository.getCountByCategory(category.get());
		if(count > 0)
		{
			double rtn = expenseRepository.getTotalCostByCategory(category.get());
			return rtn;
		}
		else return 0.0;
	}
	
	@Override
	public double getTotalCostBetweenTwoDates(String startDate, String endDate) {
		return expenseRepository.getTotalCostBetweenTwoDates(LocalDate.parse(startDate), LocalDate.parse(endDate), userService.getLoggedinUser());
	}
	
	@Override
	public double getTotalCostBetweenTwoDatesByCategory(String startDate, String endDate, Long categoryId) {
		Category c = categoryRepository.getCategoryById(categoryId);
		System.out.println(c.getCategory());
		System.out.println(userService.getLoggedinUser().getUserName());
		return expenseRepository.getTotalCostBetweenTwoDatesByCategory(LocalDate.parse(startDate), LocalDate.parse(endDate), c, userService.getLoggedinUser());
	}
	
	@Override
	public ArrayList<ArrayList<Object>> getTimeseriesChart() {
		ArrayList<ArrayList<Object>> chart = new ArrayList<>();
		
		ArrayList<Object> dates = new ArrayList<>();
		dates.add("Dates");
		LocalDate earliest_date = expenseRepository.getEarliestDate(userService.getLoggedinUser());
		if (earliest_date == null) {
			return new ArrayList<ArrayList<Object>>();
		}
		earliest_date = earliest_date.with(TemporalAdjusters.firstDayOfMonth());
		LocalDate first_of_current_month = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
		
		long monthsBetween = ChronoUnit.MONTHS.between(YearMonth.from(earliest_date), YearMonth.from(first_of_current_month));
		
		LocalDate date = earliest_date.with(TemporalAdjusters.firstDayOfMonth());
		ArrayList<Object> total_costs_per_month = new ArrayList<Object>();
		total_costs_per_month.add("Total");
		for (int i = 0; i <= monthsBetween; i++) {
			dates.add(date.toString());
			LocalDate end_date = date.with(TemporalAdjusters.lastDayOfMonth());
			try {
				total_costs_per_month.add(expenseRepository.getTotalCostBetweenTwoDates(date, end_date, userService.getLoggedinUser()));
			} catch (Exception e) {
				// No data for a given month results in a null return from the expenseRepository
				// Return null so the timeseries chart does not plot a point for the given month
				total_costs_per_month.add(null);
			}
			date = date.plusMonths(1).with(TemporalAdjusters.firstDayOfMonth());		
		}
		
		chart.add(dates);
		
		ArrayList<Category> categories = new ArrayList<Category>(categoryRepository.findAllByUser(userService.getLoggedinUser()));
		
		// Calculate the costs per month for every category, one ArrayList per category
		for (Category c : categories) {
			boolean addData = false;
			ArrayList<Object> costs_per_month = new ArrayList<Object>();
			costs_per_month.add(c.getCategory());
			for (int i = 1; i < dates.size(); i++) {
				LocalDate curr_month_start = LocalDate.parse((String)dates.get(i));
				LocalDate curr_month_end = curr_month_start.with(TemporalAdjusters.lastDayOfMonth());
				
				try {
					double cost_this_month_for_this_category = expenseRepository.getTotalCostBetweenTwoDatesByCategory(curr_month_start, curr_month_end, c, userService.getLoggedinUser());
					costs_per_month.add(cost_this_month_for_this_category);
					addData = true;
				} catch (Exception e) {
					// No data for a given month results in a null return from the expenseRepository
					// Return null so the timeseries chart does not plot a point for the given month
					costs_per_month.add(0);
					addData |= false;
				}
			}
			if (addData) {
				chart.add(costs_per_month);
			}
		}
		
		return chart;
	}
	
	@Override
	public double getTotalExpenseToLimitRatio() {
		double total_expense = expenseRepository.getTotalCost(userService.getLoggedinUser());
		System.out.println(total_expense);
		Limit total_limit = limitRepository.getTotalLimit(userService.getLoggedinUser());
		if (total_limit == null) {
			return -1;
		}
		System.out.println(total_limit.getLimit());
		
		return total_expense / total_limit.getLimit() * 100;
	}
}
