package bg.car_wash.configurations.database;

import bg.car_wash.areas.car.entity.CarMakeModel;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

public class InsertCars {

	private Gson gson;

	public InsertCars() throws FileNotFoundException {
		this.gson = new Gson();
	}

	public List<CarMakeModel> readJsonFile() throws FileNotFoundException {
		JsonReader reader = new JsonReader(new FileReader("/home/ivanof/cars.json"));
		CarMakeModel[] carMakeModels = gson.fromJson(reader, CarMakeModel[].class);
		List<CarMakeModel> carMakeModel = Arrays.asList(carMakeModels);

		return carMakeModel;
	}
}
