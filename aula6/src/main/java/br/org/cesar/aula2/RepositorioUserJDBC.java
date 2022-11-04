package br.org.cesar.aula2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RepositorioUserJDBC {
	private static final String URL_BANCO = "jdbc:mysql://localhost:3306/payroll";
	private static final String USER_BANCO = "root";
	private static final String PWD_BANCO = "xpto";
	
	User save(User user) throws Exception {
		if (user.getId() == null || user.getId() == 0) {
			return insert(user);
		} else {
			return update(user);
		}
	}	
	private User insert(User user) throws Exception {
		Connection con = DriverManager.getConnection(URL_BANCO, USER_BANCO, PWD_BANCO);
		PreparedStatement ps = con.prepareStatement("insert into USR_USER (USR_CPF,USR_NOME) values (?,?)",
				Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, user.getCpf());
		ps.setString(2, user.getNome());
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		if (rs.next()) {
			long id = rs.getLong(1);
			user.setId(id);
		}
		return user;
	}
	private User update(User user) throws Exception {
		Connection con = DriverManager.getConnection(URL_BANCO, USER_BANCO, PWD_BANCO);
		PreparedStatement ps = con.prepareStatement("update USR_USER set USR_CPF=?,USR_NOME=? where USR_ID=?");
		ps.setString(1, user.getCpf());
		ps.setString(2, user.getNome());
		ps.setLong(3, user.getId());
		int qtdLinhasAfetadas = ps.executeUpdate();
		if (qtdLinhasAfetadas == 0) { // id não existe, a cláusula where do UPDATE não encontrou registro
			return null; 
		} else {
			return user;
		}
	}
	void deleteById(long id) throws Exception {
		Connection con = DriverManager.getConnection(URL_BANCO, USER_BANCO, PWD_BANCO);
		PreparedStatement ps = con.prepareStatement("delete from USR_USER where USR_ID=?");
		ps.setLong(1, id);
		ps.executeUpdate();
	}
	Optional<User> findById(long id) throws Exception {
		Optional<User> opt; 
		Connection con = DriverManager.getConnection(URL_BANCO, USER_BANCO, PWD_BANCO);
		PreparedStatement ps = con.prepareStatement("select USR_ID, USR_CPF, USR_NOME from USR_USER where USR_ID=?");
		ps.setLong(1, id);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			User user = new User(rs.getString(2), rs.getString(3));
			user.setId(rs.getLong(1));
			opt = Optional.of(user);
		} else {
			opt = Optional.empty();
		}
		return opt;
	}
	List<User> findAll() throws Exception {
		List<User> users = new ArrayList<User>();
		Connection con = DriverManager.getConnection(URL_BANCO, USER_BANCO, PWD_BANCO);
		PreparedStatement ps = con.prepareStatement("select USR_ID, USR_CPF, USR_NOME from USR_USER");
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			User user = new User(rs.getString(2), rs.getString(3));
			user.setId(rs.getLong(1));
			users.add(user);
		}
		return users;
	}
}
