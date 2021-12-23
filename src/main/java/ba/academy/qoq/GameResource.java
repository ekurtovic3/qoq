package ba.academy.qoq;

import ba.academy.qoq.dto.DungeonDto;
import ba.academy.qoq.dto.GameDto;
import ba.academy.qoq.dto.WeightFacotr;
import ba.academy.qoq.services.GameSerivce;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/game")
public class GameResource {
    @Inject
    GameSerivce gameSerivce;

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response createGame(WeightFacotr weightFacotr) {
        GameDto game = gameSerivce.createGame(weightFacotr);
        if (game != null) {
            return Response.status(Response.Status.OK).entity(game).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @POST
    @Path("/{id}/move")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response move(@PathParam("id") int id) {

        DungeonDto dungeonDto = gameSerivce.move(id);
        String massage = "{\"Poruka\":\"Presli ste u iduci dungeon\"";
        if(dungeonDto.getMonster()!=null) massage+=",\n\"Monster\":\"Monster\"}";
        return Response.status(200).entity(dungeonDto).build();
    }

    @POST
    @Path("/{id}/fight")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response fight() {


        return Response.status(Response.Status.OK).build();
    }

    @POST
    @Path("/{id}/flee")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response flee() {


        return Response.status(Response.Status.OK).build();
    }

    @POST
    @Path("/{id}/heal")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response heal() {


        return Response.status(Response.Status.OK).build();
    }
}
