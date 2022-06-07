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
    public class ReclamoConductorController : Controller
    {
        [HttpPost]
        [Route("api/[controller]/lista")]
        public List<ReclamoConductor> ListarReclamosConductores(Filtro oFiltro)
        {
            return ReclamoConductorRepositorio.ListarReclamosConductores(oFiltro);
        }

        [HttpPost]
        [Route("api/[controller]/insertar")]
        public bool InsertarReclamoConductor([FromBody] ReclamoConductor oReclamoConductor)
        {
            return ReclamoConductorRepositorio.InsertarReclamoConductor(oReclamoConductor);
        }

        [HttpPost]
        [Route("api/[controller]/cambiar")]
        public bool CambiarEstadoReclamoConductor([FromBody] ReclamoConductor oReclamoConductor)
        {
            return ReclamoConductorRepositorio.CambiarEstadoReclamoConductor(oReclamoConductor);
        }

    }
}