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
        String massage=massageMove(dungeonDto);
        return Response.status(200).entity(massage).build();
    }

    @POST
    @Path("/{id}/fight")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response fight(@PathParam("id") int id) {

        int winer = gameSerivce.fight(id);
        String massage=null;
        if(winer==0) massage = "{\"FIGHT\":\"You win fight\"}";
        if(winer==1) massage = "{\"FIGHT\":\"Moster win, GAME OVER\"}";
        return Response.status(Response.Status.OK).entity(massage).build();
    }

    @POST
    @Path("/{id}/flee")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response flee(@PathParam("id") int id) {
        String massage="";
        DungeonDto dungeonDto = gameSerivce.flee(id);
        if(dungeonDto==null) return Response.status(Response.Status.OK).entity("{\"Game\":\"GAME OVER\"}").build();
        massage = massageFlee(dungeonDto);
       return Response.status(Response.Status.OK).entity(massage).build();
    }

    @POST
    @Path("/{id}/heal")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response heal(@PathParam("id") int id) {
        PlayerDto playerDto = gameSerivce.heal(id);
        return Response.status(Response.Status.OK).entity(playerDto).build();
    }

    private String massageMove(DungeonDto dungeonDto){
        String massage = "{\"Massage\":\"You've moved on to the next dungeon\"";
        if( dungeonDto.getMonster()!=null) massage+=",\n\"Monster\":\"There is a monster in this dungeon\"";
        if( dungeonDto.getItem() instanceof HelaerDto && dungeonDto.getMonster()==null) massage+=",\n\"Healer\":\"You picked up the healer\"";
        if(dungeonDto.getItem() instanceof PowerUpDto && dungeonDto.getMonster()==null) massage+=",\n\"PowerUp\":\"You picked up powe up.\"";
        massage+="}";
        if( dungeonDto.getItem() instanceof QoqDto && dungeonDto.getMonster()==null) massage="{\"Game\":\"QOQ-YOU WIN\"}";
        if(dungeonDto.getId()==0) massage="{\"END\":\"You have reached the end of the dungeons-GAME OVER\"}";
        return massage;
    }
    private String massageFlee(DungeonDto dungeonDto){
        String massage = "{\"Massage\":\"You've moved on to the next dungeon\"";
        if( dungeonDto.getMonster()!=null) massage+=",\n\"Monster\":\"There is a monster in this dungeon\"";
        if( dungeonDto.getItem() instanceof HelaerDto && dungeonDto.getMonster()==null) massage+=",\n\"Healer\":\"You picked up the healer\"";
        if(dungeonDto.getItem() instanceof PowerUpDto && dungeonDto.getMonster()==null) massage+=",\n\"PowerUp\":\"You picked up powe up.\"";
        massage+="}";
        if( dungeonDto.getItem() instanceof QoqDto && dungeonDto.getMonster()==null) massage="{\"Game\":\"QOQ-YOU WIN\"}";
        if(dungeonDto.getId()==0) massage="{\"END\":\"You have reached the end of the dungeons-GAME OVER\"}";
        return massage;

    }
}
