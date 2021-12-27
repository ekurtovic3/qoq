package ba.academy.qoq;

import ba.academy.qoq.dto.*;
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
        if(dungeonDto.getMonster()!=null) massage+=",\n\"Monster\":\"Ovdje se nalazi cudoviste.\"";
        if(dungeonDto.getItem() instanceof HelaerDto) massage+=",\n\"Healer\":\"U ovoj sobi se nalazi healer.\"";
        if(dungeonDto.getItem() instanceof PowerUpDto) massage+=",\n\"PowerUp\":\"U ovoj se nalazi power up.\"";
        if(dungeonDto.getItem() instanceof QoqDto) massage+=",\n\"PowerUp\":\"U ovoj se nalazi QOQ\"";

        massage+="}";
        return Response.status(200).entity(massage).build();
    }

    @POST
    @Path("/{id}/fight")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response fight(@PathParam("id") int id) {

        int winer = gameSerivce.fight(id);
        String massage=null;
        if(winer==0) massage = "{\"Win\":\"Player\"}";
        if(winer==1) massage = "{\"Win\":\"Moster\"}";
        return Response.status(Response.Status.OK).entity(massage).build();
    }

    @POST
    @Path("/{id}/flee")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response flee(@PathParam("id") int id) {


        DungeonDto dungeonDto = gameSerivce.flee(id);
        String massage = "{\"Poruka\":\"Presli ste u iduci dungeon\"";
        if(dungeonDto.getMonster()!=null) massage+=",\n\"Monster\":\"Ovdje se nalazi cudoviste.\"";
        if(dungeonDto.getItem() instanceof HelaerDto) massage+=",\n\"Healer\":\"U ovoj sobi se nalazi healer.\"";
        if(dungeonDto.getItem() instanceof PowerUpDto) massage+=",\n\"PowerUp\":\"U ovoj se nalazi power up.\"";
        if(dungeonDto.getItem() instanceof QoqDto) massage+=",\n\"PowerUp\":\"U ovoj se nalazi QOQ\"";

        massage+="}";
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
