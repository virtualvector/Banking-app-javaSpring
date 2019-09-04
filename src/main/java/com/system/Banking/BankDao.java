package com.system.Banking;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("bankDao")
public class BankDao {

	DataSource ds;

	JdbcTemplate jdbcTemplate; // = new JdbcTemplate();

	String qry;

	public DataSource getDs() {
		return ds;
	}

	@Autowired
	public void setDs(DataSource ds) {
		jdbcTemplate = new JdbcTemplate(ds);
	}

//	
//	//For Fetching the count of rows from table
//	int getCount() {
//		qry = "select count(*) from accountDetails";
//		
//		return jdbcTemplate.queryForObject(qry, Integer.class);
//		
//	}
//	
	String getBalance(String accN) {
		qry = "select userName from accountDetails where accountNumber = ? ";

		return jdbcTemplate.queryForObject(qry, new Object[] { accN }, String.class);

	}
//	

	// Get All Users
	List<User> getAllUsers() {
		qry = "select * from accountDetails";

		return jdbcTemplate.query(qry, new UserMapper());
	}

//	
	void insert(User user) {
		String trans;
		Object[] params = { user.getAccountNumber(), user.getUserName(), user.getPassword(), user.getBalance(),
				user.getTransaction() };
		qry = "insert into accountDetails(accountNumber,userName,password,balance,transaction) values (?,?,?,?,?)";

		jdbcTemplate.update(qry, params);

	}

//	
	void updateBalance(String id, int bal, int deposit) {

		String trans = " ";

		List<User> all = getAllUsers();

		if (deposit == 1) {

			for (User user : all) {
				if (user.getAccountNumber().compareTo(id) == 0) {
					trans = user.getTransaction();

					Object[] params = { user.getBalance() + bal, trans + "," + "Credited" + Integer.toString(bal), id };
					qry = "update  accountDetails set balance = ? , transaction= ? where accountNumber= ?";

					jdbcTemplate.update(qry, params);

				}
			}

		} else {

			for (User user : all) {
				if (user.getAccountNumber().compareTo(id) == 0) {
					trans = user.getTransaction();

					Object[] params = { user.getBalance() - bal, trans + "," + "Debited" + Integer.toString(bal), id };
					qry = "update  accountDetails set balance = ? , transaction= ? where accountNumber= ?";

					jdbcTemplate.update(qry, params);

				}

			}

		}

	}

//	transfer amount
	
	void transfer(String id, String id2, int amt) {

		String trans = " ";

		List<User> all = getAllUsers();

		

			for (User user : all) {
				if (user.getAccountNumber().compareTo(id) == 0) {
					trans = user.getTransaction();

					Object[] params = { user.getBalance() - amt, trans + "," + "sent to "+id2+ " " + Integer.toString(amt), id };
					qry = "update  accountDetails set balance = ? , transaction= ? where accountNumber= ?";

					jdbcTemplate.update(qry, params);

				}
			}
			
			
			for (User user : all) {
				if (user.getAccountNumber().compareTo(id2) == 0) {
					trans = user.getTransaction();

					Object[] params = { user.getBalance() + amt, trans + "," + "received from "+id+ " " + Integer.toString(amt), id2 };
					qry = "update  accountDetails set balance = ? , transaction= ? where accountNumber= ?";

					jdbcTemplate.update(qry, params);

				}
			}

		 

	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	
	void delete(String accNum) {
		Object[] params = { accNum };
		qry = "delete from  accountDetails where accountNumber=?";

		jdbcTemplate.update(qry, params);
	}
//	

	// Create a inner class that implements RowMapper

	class UserMapper implements RowMapper<User> {

		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();

			// Map the fields of User with columns of table
			user.setUserName(rs.getString("userName"));
			user.setAccountNumber(rs.getString("accountNumber"));
			user.setBalance(rs.getInt("balance"));
			user.setTransaction(rs.getString("transaction"));

			return user;
		}

	}

}
