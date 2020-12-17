package DAo;

import java.sql.Connection;
import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.tomcat.util.descriptor.web.SessionConfig;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.met.model.Employee;

//@Component
@Repository
public class EmployeeDAO<jdbcTemplate> {

	public class Session {

	}

	private Map<Integer, EmployeeDAO> mapEmployees = new HashMap<>();
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private void saveUsingDataSource(EmployeeDAO employee){
		
		//Connection con = null;
		PreparedStatement pstmt = null;
		
		/*try(Connection conn = dataSource.getConnection()){
			
		}*/
		
		try(Connection con = dataSource.getConnection()){				//GetConnection
			//con = dataSource.getConnection();		
			pstmt = con.prepareStatement("insert into EmployeeTbl values(?, ?, ?, ?)");		//Create PS
			pstmt.setInt(1, employee.getId());
			pstmt.setString(2, employee.getName());
			pstmt.setString(3, employee.getDesignation());
			pstmt.setString(4, employee.getEmailId());
			
			pstmt.executeUpdate();				//call executeUpdate
			
			
		}catch(SQLException ex){
			ex.printStackTrace();				//Handle exception
		}/*finally {
			if(pstmt != null){
				
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}*/
		
	}
	
	
	private String getDesignation() {
		// TODO Auto-generated method stub
		return null;
	}


	private String getEmailId() {
		// TODO Auto-generated method stub
		return null;
	}


	private String getName() {
		// TODO Auto-generated method stub
		return null;
	}


	private int getId() {
		// TODO Auto-generated method stub
		return 0;
	}


	private void saveUsingJDBCTemplate(EmployeeDAO employee){
		
		jdbcTemplate.update("insert into EmployeeTbl values(?, ?, ?, ?)", 
				new Object[]{employee.getId(), employee.getName(), employee.getDesignation(), employee.getEmailId()});
		
	}

	private void saveUsingHibernate(Employee employee){
		
		Session session = null;		
		Transaction tx = null;
		
		try{
			
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			session.save(employee);				
			tx.commit();
			
		}catch(HibernateException ex){			
			if(tx != null) tx.rollback();
			
			ex.printStackTrace();
			
		}finally {								//@Finally
			if(session != null) session.close();
		}
		
		//AOP
		
	}
	
	@Transactional(rollbackFor=Throwable.class)		// POINTCUT for transaction management
	public void saveEmployee(Employee employee){
		
		//mapEmployees.put(employee.getId(), employee);
		
		//saveUsingDataSource(employee);
		
		//saveUsingJDBCTemplate(employee);
		
		//saveUsingHibernate(employee);
		
		Session session = sessionFactory.getCurrentSession();			//If there is hibernate session inside
																		//Thread Localcontext			
		session.save(employee);
		
		updateEmployeeCount();
		
	}
	
	//@Transactional(propagation=Propagation.NEVER)
	public void updateEmployeeCount(){
		
		SessionConfig session = sessionFactory.getCurrentSession();
		session.createSQLQuery("update EmployeeCount set count=count+1").executeUpdate();
		
	}
	
	
	public static Collection<EmployeeDAO> getAllEmployees(){
		
		
		
		return JDBCType.query("select * from employeetbl", new BeanPropertyRowMapper<Employee>(EmployeeDAO.class));
		
	}
	
	public EmployeeDAO getEmployee(int id){
		return JDBCType.queryForObject("select * from employeetbl where id=?", new Object[]{id}, 
				new BeanPropertyRowMapper<Employee>(Employee.class));
	}
	
}
