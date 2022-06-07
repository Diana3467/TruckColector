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
    public class AdministradorRepositorio
    {
        public static string AutentificarAdministrador(Administrador oAdministrador)
        {
            string sp = StoredProcedure.USP_AUTENTIFICAR_ADMINISTRADOR;

            using (SqlConnection oConexion = new SqlConnection(ConexionBD.rutaConexion))
            {
                string respuesta = "";
                SqlCommand parametros = new SqlCommand(sp, oConexion);
                parametros.CommandType = CommandType.StoredProcedure;
                parametros.Parameters.AddWithValue("@x_cDNIAdmParameter", oAdministrador.cDNIAdm.ToString());
                parametros.Parameters.AddWithValue("@x_cPassAdmParameter", oAdministrador.cPassAdm.ToString());

                try
                {
                    oConexion.Open();

                    using (SqlDataReader dr = parametros.ExecuteReader())
                    {
                        while (dr.Read())
                        {
                            respuesta = dr["cMenAutAdm"].ToString();
                        }

                    }
                    return respuesta;
                }
                catch (IOException e)
                {
                    return respuesta;
                }
                finally
                {
                    oConexion.Close();
                }
            }
        }


        public static Administrador TraerDatosAdministrador(string cDNIAdm)
        {
            string sp = StoredProcedure.USP_TRAER_UNO_ADMINISTRADOR;
            Administrador oAdministrador = new Administrador();

            using (SqlConnection oConexion = new SqlConnection(ConexionBD.rutaConexion))
            {

                SqlCommand parametros = new SqlCommand(sp, oConexion);
                parametros.CommandType = CommandType.StoredProcedure;
                parametros.Parameters.AddWithValue("@x_cDNIAdmParameter", cDNIAdm.ToString());

                try
                {
                    oConexion.Open();

                    using (SqlDataReader dr = parametros.ExecuteReader())
                    {
                        while (dr.Read())
                        {
                            oAdministrador.nCodigoAdm = dr["nCodigoAdm"].ToString();
                            oAdministrador.cNombreAdm = dr["cNombreAdm"].ToString();
                            oAdministrador.cApePatAdm = dr["cApePatAdm"].ToString();
                            oAdministrador.cApeMatAdm = dr["cApeMatAdm"].ToString();
                            oAdministrador.cDNIAdm = dr["cDNIAdm"].ToString();
                            oAdministrador.cCorEleAdm = dr["cCorEleAdm"].ToString();
                            oAdministrador.lEstadoAdm = dr["lEstadoAdm"].ToString();
                        }

                    }
                    return oAdministrador;
                }
                catch (IOException e)
                {
                    return oAdministrador;
                }
                finally
                {
                    oConexion.Close();
                }
            }
        }

        public static bool Actualizar(Administrador oAdministrador)
        {
            string sp = StoredProcedure.USP_ACTUALIZAR_DATOS_ADMINISTRADOR;

            using (SqlConnection oConexion = new SqlConnection(ConexionBD.rutaConexion))
            {
                bool respuesta = false;
                SqlCommand parametros = new SqlCommand(sp, oConexion);
                parametros.CommandType = CommandType.StoredProcedure;
                parametros.Parameters.AddWithValue("@x_nCodigoAdm", oAdministrador.nCodigoAdm.ToString());
                parametros.Parameters.AddWithValue("@x_cNombreAdm", oAdministrador.cNombreAdm.ToString());
                parametros.Parameters.AddWithValue("@x_cApePatAdm", oAdministrador.cApePatAdm.ToString());
                parametros.Parameters.AddWithValue("@x_cApeMatAdm", oAdministrador.cApeMatAdm.ToString());
                parametros.Parameters.AddWithValue("@x_cDNIAdm", oAdministrador.cDNIAdm.ToString());
                parametros.Parameters.AddWithValue("@x_cCorEleAdm", oAdministrador.cCorEleAdm.ToString());
                parametros.Parameters.AddWithValue("@x_cPassAdm", oAdministrador.cDNIAdm.ToString());

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





    }
}
