using ServicesWeb.Modelos;
using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.IO;
using System.Linq;
using System.Threading.Tasks;

namespace ServicesWeb.Repositorio
{
    public class DenunciaCiudadanoRepositorio
    {
        public static bool InsertarDenunciaCiudadano(DenunciaCiudadano oDenunciaCiudadano)
        {
            string sp = StoredProcedure.USP_INSERTAR_DENUNCIA;

            using (SqlConnection oConexion = new SqlConnection(ConexionBD.rutaConexion))
            {
                bool respuesta = false;
                SqlCommand parametros = new SqlCommand(sp, oConexion);
                parametros.CommandType = CommandType.StoredProcedure;

                parametros.Parameters.AddWithValue("@x_cModoDenC", oDenunciaCiudadano.cModoDenC.ToString());
                parametros.Parameters.AddWithValue("@x_cFechaDenC", oDenunciaCiudadano.cFechaDenC.ToString());
                parametros.Parameters.AddWithValue("@x_cUbicacionDenC", oDenunciaCiudadano.cUbicacionDenC.ToString());
                parametros.Parameters.AddWithValue("@x_cDescripcionDenC", oDenunciaCiudadano.cDescripcionDenC.ToString());
                parametros.Parameters.AddWithValue("@x_nCodigoCiud", oDenunciaCiudadano.nCodigoCiud.ToString());

                try
                {
                    oConexion.Open();
                    parametros.ExecuteNonQuery();

                    respuesta = true;
                    return respuesta;
                }
                catch (IOException e)
                {
                    respuesta = false;
                    return respuesta;
                }
                finally
                {
                    oConexion.Close();
                }
            }
        }

        public static bool CambiarEstadoDenunciaCiudadano(DenunciaCiudadano oDenunciaCiudadano)
        {
            string sp = StoredProcedure.USP_CAMBIAR_ESTADO_DENUNCIA;

            using (SqlConnection oConexion = new SqlConnection(ConexionBD.rutaConexion))
            {
                bool respuesta = false;
                SqlCommand parametros = new SqlCommand(sp, oConexion);
                parametros.CommandType = CommandType.StoredProcedure;

                parametros.Parameters.AddWithValue("@x_nCodigoDenC", oDenunciaCiudadano.nCodigoDenC.ToString());

                try
                {
                    oConexion.Open();
                    parametros.ExecuteNonQuery();

                    respuesta = true;
                    return respuesta;
                }
                catch (IOException e)
                {
                    respuesta = false;
                    return respuesta;
                }
                finally
                {
                    oConexion.Close();
                }
            }
        }



        public static List<DenunciaCiudadano> ListarDenuncias(Filtro oFiltro)
        {
            List<DenunciaCiudadano> oDenunciaCiudadano = new List<DenunciaCiudadano>();

            string sp = StoredProcedure.USP_LISTAR_FILTRO_DENUNCIA;

            using (SqlConnection oConexion = new SqlConnection(ConexionBD.rutaConexion))
            {

                SqlCommand parametros = new SqlCommand(sp, oConexion);
                parametros.CommandType = CommandType.StoredProcedure;
                parametros.Parameters.AddWithValue("@x_filtroNuevo_Visto_Todos", oFiltro.filtroNuevo_Visto_Todos.ToString());
                parametros.Parameters.AddWithValue("@x_filtroAnonimo_Publico_Todos", oFiltro.filtroAnonimo_Publico_Todos.ToString());

                try
                {
                    oConexion.Open();

                    using (SqlDataReader dr = parametros.ExecuteReader())
                    {
                        while (dr.Read())
                        {
                            oDenunciaCiudadano.Add(new DenunciaCiudadano()
                            {
                                nCodigoDenC = dr["nCodigoDenC"].ToString(),
                                cModoDenC = dr["cModoDenC"].ToString(),
                                cFechaDenC = dr["cFechaDenC"].ToString(),
                                cUbicacionDenC = dr["cUbicacionDenC"].ToString(),
                                cDescripcionDenC = dr["cDescripcionDenC"].ToString(),
                                lEstadoDenCo = dr["lEstadoDenCo"].ToString(),
                                nCodigoCiud = dr["nCodigoCiud"].ToString(),

                            });
                        }

                    }
                    return oDenunciaCiudadano;
                }
                catch
                {
                    return oDenunciaCiudadano;
                }
                finally
                {
                    oConexion.Close();
                }
            }
        }






    }
}