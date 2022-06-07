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
    public class RutaController : Controller
    {

        // GET: api/<RutaController>
        [HttpGet]
        [Route("api/[controller]/listaruta")]
        public List<Ruta> ListarRutas()
        {
            return RutaRepositorio.ListarRutas();
        }
    }
}
