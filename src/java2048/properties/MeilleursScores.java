package java2048.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import java2048.model.Modele;

public class MeilleursScores
	{

		private static final String fileName = "game2048.properties";

		private static final String	highCell	= "highCell";
		private static final String	highScore	= "highScore";

		private Modele modele;

		public MeilleursScores(Modele modele)
		{
			this.modele = modele;
		}

		public void loadProperties()
		{
			Properties properties = new Properties();

			InputStream is = null;
			File file = new File(fileName);
			try
			{
				is = new FileInputStream(file);
				properties.load(is);
				modele.setMeilleureScore(Integer.parseInt(properties.getProperty(highScore)));
				modele.setCelluleMax(Integer.parseInt(properties.getProperty(highCell)));
			} catch (FileNotFoundException e)
			{

			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}

		public void saveProperties()
		{
			Properties properties = new Properties();
			properties.setProperty(highScore, Integer.toString(modele.getMeilleureScore()));
			properties.setProperty(highCell, Integer.toString(modele.getCelluleMax()));

			OutputStream os = null;
			File file = new File(fileName);

			try
			{
				os = new FileOutputStream(file);
				properties.store(os, "2048 High Score");
				os.flush();
			} catch (FileNotFoundException e)
			{
				e.printStackTrace();
			} catch (IOException e)
			{
				e.printStackTrace();
			}

			try
			{
				if (os != null)
				{
					os.close();
				}
			} catch (IOException e)
			{
				e.printStackTrace();
			}

		}
	}