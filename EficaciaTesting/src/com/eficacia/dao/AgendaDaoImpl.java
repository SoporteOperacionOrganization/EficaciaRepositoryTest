package com.eficacia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.object.SqlQuery;
import org.springframework.stereotype.Repository;

import com.eficacia.model.Agenda;

@Repository
public class AgendaDaoImpl implements AgendaDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session session;
	private Query query;
	
	@Override
	public Agenda obtenerAgenda(String codigoTransaccion) {
		session = sessionFactory.getCurrentSession();
		query = session.createQuery("FROM Agenda a where a.codigoTransaccion = :codigoTransaccion");
		query.setParameter("codigoTransaccion", codigoTransaccion);
		Agenda agenda=(Agenda)query.uniqueResult();
		return agenda;
		/*session = sessionFactory.getCurrentSession();
		List<Agenda> agendas = new ArrayList<Agenda>();
		query = session.createQuery("FROM Agenda a where a.codigoTransaccion = :codigoTransaccion");
		query.setParameter("codigoTransaccion", codigoTransaccion);
		agendas = query.list();
		if(agendas.size() > 0){
			return agendas.get(0);
		}else{
			return null;
		}*/
		
	}
	
	@Override
	public List<Agenda> obtenerAgendas(String razonSocial) {
		List<Agenda> agendas = new ArrayList<Agenda>();
		session = sessionFactory.getCurrentSession();
		
		if("all".equals(razonSocial)){
			query = session.createQuery("FROM Agenda");
			
		}else{
			query = session.createQuery("FROM Agenda a where a.razonSocial like :razonSocial");
			query.setParameter("razonSocial", "%"+razonSocial+"%");
		}
		
		agendas = query.list();
		return agendas;
	}

	@Override
	public Long contarRegistros() {
		session = sessionFactory.getCurrentSession();
		query = session.createQuery("SELECT count(a) from Agenda a");
		Long count = (Long) query.uniqueResult();
		return count;
	}
	
	@Override
	public Long contarRegistrosPag(String razonSocial) {
		session = sessionFactory.getCurrentSession();
		query = session.createQuery("SELECT count(a) from Agenda a WHERE a.razonSocial like :razonSocial");
		query.setParameter("razonSocial", "%"+razonSocial+"%");
		Long count = (Long) query.uniqueResult();
		return count;
	}

	@Override
	public void agregarAgenda(Agenda agenda) {
		session = sessionFactory.getCurrentSession();
		session.save(agenda);
	}

	@Override
	public void eliminarAgenda(String codigoTransaccion){
		 session = sessionFactory.getCurrentSession();
		 Agenda agenda = obtenerAgenda(codigoTransaccion);
		 session.delete(agenda);
		 //query = session.createQuery("DELETE FROM Agenda a WHERE a.codigoTransaccion = :codigoTransaccion");
		 //query.setParameter("codigoTransaccion", codigoTransaccion);
		 //query.executeUpdate();
	}
	
	@Override
	public List<Agenda> filtrarAgendas(String razonSocial, Integer offset, Integer limite) {
		List<Agenda> agendas;
		session = sessionFactory.getCurrentSession();
		query = session.createQuery("FROM Agenda a where a.razonSocial like :razonSocial");
		query.setParameter("razonSocial", "%"+razonSocial+"%");
		query.setFirstResult(offset!=null?offset:0);
		query.setMaxResults(limite!=null?limite:15);
		agendas = query.list();
		return agendas;
	}

	@Override
    public String cargaMasiva(List<Agenda> agendas) {

		/*int tamano = agendas.size();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		for(int i = 0; i < tamano; i++){
			session.save(agendas.get(i));
			if(i % 50 == 0){
				session.flush();
				session.clear();
			}
		}
		tx.commit();
		session.close();*/
		
		
		
		Session session = null;
		session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.doWork(new Work(){
            @Override
            public void execute(Connection connection) throws SQLException {
            	PreparedStatement pstmt = null;
            	
            	try{

            	String sqlQuery = "insert into agenda(agendaCodigoTransaccion, agendaFechaTransaccion, agendaFechaCiere, agendaNumeroCliente, agendaRazonSocial,"
        				+ "agendaNombreRepresentante, agendaNumeroTelefono, agendaSoeid, agendaEjecutivo, agendaSede) values "
        				+ "(?,?,?,?,?,?,?,?,?,?)";
            	
            	pstmt = connection.prepareStatement(sqlQuery);
            	System.out.println("PerpareStatement " );
            	int tamano = agendas.size();
            	for(int i = 0; i < tamano; i++){
        			pstmt.setString(1, agendas.get(i).getCodigoTransaccion());
        			pstmt.setString(2, agendas.get(i).getFechaTransaccion());
        			pstmt.setString(3, agendas.get(i).getFechaCierre());
        			pstmt.setString(4, agendas.get(i).getNumeroCliente());
        			pstmt.setString(5, agendas.get(i).getRazonSocial());
        			pstmt.setString(6, agendas.get(i).getNombreRepresentante());
        			pstmt.setString(7, agendas.get(i).getNumeroTelefono());
        			pstmt.setString(8, agendas.get(i).getSoeid());
        			pstmt.setString(9, agendas.get(i).getEjecutivo());
        			pstmt.setString(10, agendas.get(i).getSede());
        			pstmt .addBatch();
        			if ( i % 50 == 0 ) { 
                        pstmt.executeBatch();
                    }
        		}
            	pstmt.executeBatch();
            	
            	}catch(SQLException ex){
            		System.out.println("Excepcion de SQL Server: " + ex.getMessage());
            	}
            	finally{
            		pstmt.close();
            	}
            	
              }
          });
		  tx.commit();
		  session.close();
		
		
    	
                 /*session = sessionFactory.openSession();
              for(Agenda age : agendas){
                 query = session.createSQLQuery("INSERT INTO agenda(agendaCodigoTransaccion, agendaFechaTransaccion, agendaFechaCiere, agendaNumeroCliente, "
                               + "agendaRazonSocial, agendaNombreRepresentante, agendaNumeroTelefono , agendaSoeid , agendaEjecutivo , agendaSede) values (?,?,?,?,?,?,?,?,?,?)");
              query.setParameter(0, age.getCodigoTransaccion());
              query.setParameter(1, age.getFechaTransaccion());
              query.setParameter(2, age.getFechaCierre());
              query.setParameter(3, age.getNumeroCliente());
              query.setParameter(4, age.getRazonSocial());
              query.setParameter(5, age.getNombreRepresentante());
              query.setParameter(6, age.getNumeroTelefono());
              query.setParameter(7, age.getSoeid());
              query.setParameter(8, age.getEjecutivo());
              query.setParameter(9, age.getSede());
              
              query.executeUpdate();
                 
                 session.flush();
                 session.clear();
              }*/
          
          return "";
    }

	
	@Override
	public List<String> eliminacionMasiva(List<String> agendas){
		
		/*session = sessionFactory.getCurrentSession();
		String hql = "delete from Agenda a where a.codigoTransaccion in :codigos";
		List<String> codigos = new ArrayList();
		int cont = 0;
		int ind = 0;
		boolean flag = false;
		while(ind < agendas.size()){
			codigos.add(agendas.get(ind));
			if(cont == 2000){
				session.createQuery(hql).setParameterList("codigos", codigos).executeUpdate();
				codigos.clear();
				cont = 0;
			}else if((agendas.size() - 1) == ind){
				session.createQuery(hql).setParameterList("codigos", codigos).executeUpdate();
			}
			if(ind % 50 == 0){
				session.flush();
				session.clear();
			}
			
			cont++;
			ind++;
		}*/
		
		
		
		List<String> codigosNoEncontrados = new ArrayList<String>();
		Session session = null;
		session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.doWork(new Work(){
            @Override
            public void execute(Connection connection) throws SQLException {
            	PreparedStatement pstmt = null;
            	try{
	            	String sqlQuery = "DELETE FROM agenda WHERE agendaCodigoTransaccion = ?";
	            	pstmt = connection.prepareStatement(sqlQuery);
	            	int tamano = agendas.size();
	            	for(int i = 0; i < tamano; i++){
	        			pstmt.setString(1, agendas.get(i));
	        			int filasAfectadas = pstmt.executeUpdate();
	        			if(filasAfectadas == 0){
	        				codigosNoEncontrados.add(agendas.get(i));
	        			}
	        		}
            	}catch(SQLException ex){
            		System.out.println("Excepcion de SQL Server: " + ex.getMessage());
            	}finally{
            		pstmt.close();
            	}            	
              }
          });
		  tx.commit();
		  session.close();
    	
		/*int tamano = agendas.size();
		String totalNoEncontrados="";
		int NoEncontrados=0;
		session = sessionFactory.getCurrentSession();
		for(int i = 0; i < tamano; i++){
			
			String codigoTransaccion = agendas.get(i).getCodigoTransaccion();
			
			query = session.createQuery("DELETE FROM Agenda a WHERE a.codigoTransaccion = :codigoTransaccion");
			query.setParameter("codigoTransaccion", codigoTransaccion);
			Integer resultado=query.executeUpdate();
			if(resultado==0){
				NoEncontrados ++;
				}
		}

		totalNoEncontrados=Integer.toString(NoEncontrados);*/
	
		return codigosNoEncontrados;
	}
	
	
	@Override
	public ArrayList<String> registrosNoEncontrados(List<Agenda> agendas){
		
			/*ArrayList<String> noEncontrados = new ArrayList<>();
			session = sessionFactory.getCurrentSession();
			String sqlQuery = "SELECT COUNT(*) FROM agenda WHERE agendaCodigoTransaccion = :codigo";
		
			SQLQuery query = session.createSQLQuery(sqlQuery);
			for(int i = 0; i < agendas.size(); i++){
				query.setParameter("codigo", agendas.get(i).getCodigoTransaccion());
				Integer count = (Integer)query.uniqueResult();
				System.out.println("Cont " + count);
			}*/
			
			
		    ArrayList<String> noEncontrados= new ArrayList<>();
			session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.doWork(new Work(){
            @Override
            public void execute(Connection connection) throws SQLException {
            	PreparedStatement pstmt = null;
            	
            	try{

            	String sqlQuery = "SELECT count(*) FROM agenda WHERE agendaCodigoTransaccion = ?";
            	ResultSet rs;
            	pstmt = connection.prepareStatement(sqlQuery);
            	int tamano = agendas.size();
            	int con = 0;
            	for(int i = 0; i < tamano; i++){
            		String codigoTransaccion = agendas.get(i).getCodigoTransaccion();
        			pstmt.setString(1, codigoTransaccion);
        			rs = pstmt.executeQuery();
        			if(rs.next()){
        				con = rs.getInt(1);
        			}
        			
        			if(con == 0){
        				noEncontrados.add(codigoTransaccion);
        			}
        		}
            	
            	}catch(SQLException ex){
            		System.out.println("RNE Excepcion de SQL Server: " + ex.getMessage());
            	}
            	finally{
            		pstmt.close();
            	}
            	
              }
          });
		  tx.commit();
		  session.close();
		
		
		
		/*int tamano = agendas.size();
		ArrayList<String> noEncontrados= new ArrayList<>();
		session = sessionFactory.getCurrentSession();
		
		for(int i = 0; i < tamano; i++){
			
			String codigoTransaccion = agendas.get(i).getCodigoTransaccion();
			
			query = session.createQuery("FROM Agenda a WHERE a.codigoTransaccion = :codigoTransaccion");
			query.setParameter("codigoTransaccion", codigoTransaccion);
			List resultado=query.list();
			
			if(resultado.isEmpty()){
				noEncontrados.add(codigoTransaccion);
		}
		}
		return noEncontrados;*/
		return noEncontrados;
	}

	@Override
	public void modificarAgenda(Agenda agenda) {
		session = sessionFactory.getCurrentSession();
		session.update(agenda);
	}

	@Override
	public List<Agenda> obtenerAgendasPaginacion(Integer offset, Integer limite) {
		session = sessionFactory.getCurrentSession();
		query = session.createQuery("FROM Agenda");
		query.setFirstResult(offset!=null?offset:0);
		query.setMaxResults(limite!=null?limite:15);
		List<Agenda> agendas = query.list();
		return agendas;
	}
	
}
