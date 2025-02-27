/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dam.tiendarelojes.daos;

import com.dam.tiendarelojes.model.Usuario;
import com.dam.tiendarelojes.util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Implementacion de la interfaz UsuarioDAO
 * 
 * @author Alvaro Duarte
 */
public class UsuarioDAOImpl implements UsuarioDAO {

    @Override
    public void guardarUsuario(Usuario usuario) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(usuario);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public Usuario obtenerUsuarioPorDni(String dni) {
        // Obtenemos el usuario por el id
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Usuario usuario = session.createQuery("FROM Usuario WHERE dni = :dni", Usuario.class)
                    .setParameter("dni", dni)
                    .uniqueResult();
            return usuario;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Usuario obtenerUsuarioPorDniConProductos(String dni) {
        // Obtenemos el usuario por el id
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Usuario usuario = session.createQuery("FROM Usuario WHERE dni = :dni", Usuario.class)
                    .setParameter("dni", dni)
                    .uniqueResult();
            if (usuario != null) {
                Hibernate.initialize(usuario.getProductos()); // Obtenemos los productos
            }
            return usuario;
        } catch (Exception e) {
            return null;
        }
    }

}
