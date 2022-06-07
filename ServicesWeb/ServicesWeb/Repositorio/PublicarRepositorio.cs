
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
    public class PublicarRepositorio 
    {
        public static bool Grabar(Publicar oPublicar)
        {
            string sp = StoredProcedure.USP_PUBLICACION_GRABAR;

            using (SqlConnection oConexion = new SqlConnection(ConexionBD.rutaConexion)) {
                bool respuesta = false;
                SqlCommand parametros = new SqlCommand(sp, oConexion);
                parametros.CommandType = CommandType.StoredProcedure; 
                parametros.Parameters.AddWithValue("@x_cTituloPub", oPublicar.cTituloPub);
                parametros.Parameters.AddWithValue("@x_cTipoPub", oPublicar.cTipoPub);
                parametros.Parameters.AddWithValue("@x_cDetallesPub", oPublicar.cDetallesPub);
                parametros.Parameters.AddWithValue("@x_nCodigoAdm", oPublicar.nCodigoAdm);

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

        public static bool Editar(Publicar oPublicar)
        {
            string sp = StoredProcedure.USP_PUBLICACION_GRABAR;

            using (SqlConnection oConexion = new SqlConnection(ConexionBD.rutaConexion))
            {
                bool respuesta = false;
                SqlCommand parametros = new SqlCommand(sp, oConexion);
                parametros.CommandType = CommandType.StoredProcedure;
                parametros.Parameters.AddWithValue("@x_nCodigoPub", oPublicar.nCodigoPub);
                parametros.Parameters.AddWithValue("@x_cTituloPub", oPublicar.cTituloPub);
                parametros.Parameters.AddWithValue("@x_cTipoPub", oPublicar.cTituloPub);
                parametros.Parameters.AddWithValue("@x_cDetallesPub", oPublicar.cTituloPub);
                parametros.Parameters.AddWithValue("@x_nCodigoAdm", oPublicar.cTituloPub);

                try
                {
                    oConexion.Open();
                    parametros.ExecuteNonQuery();

                    respuesta = true;
                    return respuesta;
                }
                catch
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

        public void Dispose()
        {
            GC.SuppressFinalize(this);
        }
    }
}
