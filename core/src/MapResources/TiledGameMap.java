package MapResources;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.yorkpiratesgame.io.YorkPirates;

public class TiledGameMap extends GameMap {

    YorkPirates game;

    public TiledMap tiledMap;
    public OrthogonalTiledMapRenderer tiledMapRenderer;
    private int mapHeight;
    private int mapWidth;

    public TiledGameMap(){
        tiledMap = new TmxMapLoader().load("GameMaps/menuMap.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        mapHeight = ((int) tiledMap.getProperties().get("tileheight")) * ((int) tiledMap.getProperties().get("height"));
        mapWidth = ((int) tiledMap.getProperties().get("tilewidth")) * ((int) tiledMap.getProperties().get("width"));
    }

    @Override
    public void render(OrthographicCamera camera) {
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void dispose() {
        tiledMap.dispose();
    }

    @Override
    public TileType getTileTypeByCoordinate(int layer, float col, float row) {
        TiledMapTileLayer.Cell cell = ((TiledMapTileLayer) tiledMap.getLayers().get(layer)).getCell((int) col, (int) row);

        if (cell != null){
            TiledMapTile tile = cell.getTile();

            if(tile != null){
                int id = tile.getId();
                return TileType.getTileTypeById(id);
            }

        }
        return null;
    }

    @Override
    public int getWidth() {
        return ((TiledMapTileLayer) tiledMap.getLayers().get(0)).getWidth();
    }

    @Override
    public int getHeight() {
        return ((TiledMapTileLayer) tiledMap.getLayers().get(0)).getHeight();
    }

    @Override
    public int getLayers() {
        return tiledMap.getLayers().getCount();
    }

    @Override
    public float getMapHeight() {
        return mapHeight;
    }

    @Override
    public float getMapWidth() {
        return mapWidth;
    }


}
