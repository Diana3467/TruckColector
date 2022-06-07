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
    public class CiudadanoRepositorio
    {
        public static Token AutentificarCiudadano(Ciudadano oCiudadano)
        {
            string sp = StoredProcedure.USP_AUTENTIFICAR_CIUDADANO;
            Token oToken = new Token();

            using (SqlConnection oConexion = new SqlConnection(ConexionBD.rutaConexion))
            {
                SqlCommand parametros = new SqlCommand(sp, oConexion);
                parametros.CommandType = CommandType.StoredProcedure;
                parametros.Parameters.AddWithValue("@x_cDNICiudParameter", oCiudadano.cDNICiud.ToString());
                parametros.Parameters.AddWithValue("@x_cPassCiudParameter", oCiudadano.cPassCiud.ToString());

                try
                {
                    oConexion.Open();

                    using (SqlDataReader dr = parametros.ExecuteReader())
                    {
                        while (dr.Read())
                        {
                            oToken.cMensajeAut = dr["cMenAutCiud"].ToString();
                        }

                    }
                    return oToken;
                }
                catch (IOException e)
                {
                    return oToken;
                }
                finally
                {
                    oConexion.Close();
                }
            }
        }

        public static Ciudadano TraerDatosCiudadano(string cDNICiud)
        {
            string sp = StoredProcedure.USP_TRAER_UNO_CIUDADANO;
            Ciudadano oCiudadano = new Ciudadano();

            using (SqlConnection oConexion = new SqlConnection(ConexionBD.rutaConexion))
            {

                SqlCommand parametros = new SqlCommand(sp, oConexion);
                parametros.CommandType = CommandType.StoredProcedure;
                parametros.Parameters.AddWithValue("@x_cDNICiudParameter", cDNICiud.ToString());

                try
                {
                    oConexion.Open();

                    using (SqlDataReader dr = parametros.ExecuteReader())
                    {
                        while (dr.Read())
                        {
                            oCiudadano.nCodigoCiud = dr["nCodigoCiud"].ToString();
                            oCiudadano.cNombreCiud = dr["cNombreCiud"].ToString();
                            oCiudadano.cApePatCiud = dr["cApePatCiud"].ToString();
                            oCiudadano.cApeMatCiud = dr["cApeMatCiud"].ToString();
                            oCiudadano.cDNICiud = dr["cDNICiud"].ToString();
                            oCiudadano.cCelCiud = dr["cCelCiud"].ToString();
                            oCiudadano.cNumDirecCiud = dr["cNumDirecCiud"].ToString();
                            oCiudadano.nCodigoCalle = dr["nCodigoCalle"].ToString();
                        }

                    }
                    return oCiudadano;
                }
                catch (IOException e)
                {
                    return oCiudadano;
                }
                finally
                {
                    oConexion.Close();
                }
            }
        }

        public static bool Actualizar(Ciudadano oCiudadano)
        {
            string sp = StoredProcedure.USP_ACTUALIZAR_DATOS_CIUDADANO;

            using (SqlConnection oConexion = new SqlConnection(ConexionBD.rutaConexion))
            {
                bool respuesta = false;
                SqlCommand parametros = new SqlCommand(sp, oConexion);
                parametros.CommandType = CommandType.StoredProcedure;
                parametros.Parameters.AddWithValue("@x_nCodigoCiud", oCiudadano.nCodigoCiud.ToString());
                parametros.Parameters.AddWithValue("@x_cNombreCiud", oCiudadano.cNombreCiud.ToString());
                parametros.Parameters.AddWithValue("@x_cApePatCiud", oCiudadano.cApePatCiud.ToString());
                parametros.Parameters.AddWithValue("@x_cApeMatCiud", oCiudadano.cApeMatCiud.ToString());
                parametros.Parameters.AddWithValue("@x_cDNICiud", oCiudadano.cDNICiud.ToString());
                parametros.Parameters.AddWithValue("@x_cCelCiud", oCiudadano.cCelCiud.ToString());
                parametros.Parameters.AddWithValue("@x_cNumDirecCiud", oCiudadano.cNumDirecCiud.ToString());
                parametros.Parameters.AddWithValue("@x_cPassCiud", oCiudadano.cDNICiud.ToString());
                parametros.Parameters.AddWithValue("@x_nCodigoCalle", oCiudadano.nCodigoCalle.ToString());

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
