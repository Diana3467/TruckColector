using Microsoft.AspNetCore.Http;
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
    public class CalleZonaController : ControllerBase
    {
        // GET: api/<AsigCondCarroController>
        [HttpPost]
        [Route("api/[controller]")]
        public List<CalleZona> ListarCalleZona(CalleZona oCalleZona)
        {
            return CalleZonaRepositorio.ListarCalleZona(oCalleZona);
        }

    }
}
