/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dam.tiendarelojes.daos;

import com.dam.tiendarelojes.dto.ProductoDto;
import com.dam.tiendarelojes.model.Producto;
import com.dam.tiendarelojes.model.Usuario;
import com.dam.tiendarelojes.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 * Implementacion de la interfaz ProductoDAO
 *
 * @author Alvaro Duarte
 */
public class ProductoDAOImpl implements ProductoDAO {

    @Override
    public void guardarProducto(Producto producto) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(producto);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public ProductoDto obtenerProducto(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Consulta HQL para obtener un producto por su id
            Query<Producto> query = session.createQuery("FROM Producto p WHERE p.id = :id", Producto.class);
            query.setParameter("id", id);
            Producto producto = query.getSingleResult();

            // Convertir Producto a ProductoDto
            ProductoDto dto = new ProductoDto();
            dto.setId(producto.getId());
            dto.setNombre(producto.getNombre());
            dto.setDetalle(producto.getDetalle());
            dto.setTipo(producto.getTipo());
            dto.setPrecio(producto.getPrecio());
            dto.setRutaFoto(producto.getFoto());

            // Comprobamos si esta comprado (tiene idUsuario)
            if (producto.getUsuario() != null) {
                dto.setEstaComprado(true);
            }

            return dto;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ProductoDto> obtenerTodosLosProductos() {

        List<ProductoDto> productosDto = new ArrayList<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Consulta HQL para obtener todos los productos
            Query<Producto> query = session.createQuery("FROM Producto", Producto.class);
            List<Producto> productos = query.getResultList();

            // Convertir Producto a ProductoDto
            for (Producto producto : productos) {
                ProductoDto dto = new ProductoDto();
                dto.setId(producto.getId());
                dto.setNombre(producto.getNombre());
                dto.setDetalle(producto.getDetalle());
                dto.setTipo(producto.getTipo());
                dto.setPrecio(producto.getPrecio());
                dto.setRutaFoto(producto.getFoto());

                // Comprobamos si esta comprado (tiene idUsuario)
                if (producto.getUsuario() != null) {
                    dto.setEstaComprado(true);
                }

                productosDto.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return productosDto;
    }

    @Override
    public List<ProductoDto> obtenerTodosLosProductosDeUnUsuarioPorDNI(int id) {

        List<ProductoDto> productosDto = new ArrayList<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Consulta HQL para obtener todos los productos
            Query<Producto> query = session.createQuery("FROM Producto p WHERE p.usuario.id = :idUsuario",
                    Producto.class);
            query.setParameter("idUsuario", id);
            List<Producto> productos = query.getResultList();

            // Convertir Producto a ProductoDto
            for (Producto producto : productos) {
                ProductoDto dto = new ProductoDto();
                dto.setId(producto.getId());
                dto.setNombre(producto.getNombre());
                dto.setDetalle(producto.getDetalle());
                dto.setTipo(producto.getTipo());
                dto.setPrecio(producto.getPrecio());
                dto.setEstaComprado(true);
                dto.setRutaFoto(producto.getFoto());

                productosDto.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return productosDto;
    }

    @Override
    public void comprarProducto(int idProducto, int idUsuario) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Consulta HQL para obtener un producto por su id
            Query<Producto> query = session.createQuery("FROM Producto p WHERE p.id = :id", Producto.class);
            query.setParameter("id", idProducto);
            Producto producto = query.getSingleResult();

            // Comprobamos si el producto esta comprado
            if (producto.getUsuario() != null) {
                System.out.println("El producto ya esta comprado");
                return;
            }

            // Obtenemos el usuario que lo compra
            Query<Usuario> query2 = session.createQuery("FROM Usuario u WHERE u.id = :id", Usuario.class);
            query2.setParameter("id", idUsuario);
            Usuario usuario = query2.getSingleResult();

            // Modificamos el producto
            producto.setUsuario(usuario);
            transaction = session.beginTransaction();
            session.save(producto);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

}
