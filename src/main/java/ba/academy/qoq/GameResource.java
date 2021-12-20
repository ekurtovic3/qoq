package ba.academy.qoq;
import ba.academy.qoq.dto.GameDto;
import ba.academy.qoq.services.GameSerivce;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;

@Path("/game")
public class GameResource {
    @Inject GameSerivce gameSerivce;


    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response createGame() {
        GameDto game=gameSerivce.createGame();
        if(game!=null)
        {
            return Response.status(Response.Status.OK).entity(game).build();
        }
       return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
