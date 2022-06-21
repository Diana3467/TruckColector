using Microsoft.AspNetCore.Mvc;
using ServicesWeb.Modelos;
using ServicesWeb.Repositorio;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace ServicesWeb.Controllers
{
    [ApiController]
    public class ReclamoCiudadanoController : Controller
    {

        [HttpPost]
        [Route("api/[controller]/lista")]
        public List<ReclamoCiudadano> ListarReclamosCiudadanos(Filtro oFiltro)
        {
            return ReclamoCiudadanoRepositorio.ListarReclamosCiudadanos(oFiltro);
        }

        [HttpPost]
        [Route("api/[controller]/insertar")]
        public bool InsertarReclamoCiudadano([FromBody] ReclamoCiudadano oReclamoCiudadano)
        {
            return ReclamoCiudadanoRepositorio.InsertarReclamoCiudadano(oReclamoCiudadano);
        }

        [HttpPost]
        [Route("api/[controller]/cambiar")]
        public bool CambiarEstadoReclamoCiudadano([FromBody] ReclamoCiudadano oReclamoCiudadano)
        {
            return ReclamoCiudadanoRepositorio.CambiarEstadoReclamoCiudadano(oReclamoCiudadano);
        }

        // POST: api/<ReclamoCiudadanoController>/lista/{nCodigoCiud}
        [HttpPost]
        [Route("api/[controller]/lista/{nCodigoCiud}")]
        public List<ReclamoCiudadano> ListarReclamosUnCiudadano(string nCodigoCiud)
        {
            return ReclamoCiudadanoRepositorio.ListarReclamosUnCiudadano(nCodigoCiud);
        }

    }
}