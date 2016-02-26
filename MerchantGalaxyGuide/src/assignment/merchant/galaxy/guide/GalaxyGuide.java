package assignment.merchant.galaxy.guide;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import assignment.merchant.galaxy.guide.utils.Codes;
import assignment.merchant.galaxy.guide.utils.Messages;

/**
 * This is the starting point towards Merchant's Guide to the Galaxy..!!
 * 
 * @author Gautam Joshi
 */
public class GalaxyGuide
{
	private Messages		msg;
	private ValidateLine	validateLine;

	public GalaxyGuide()
	{
		msg = new Messages();
		validateLine = new ValidateLine();
	}

	/**
	 * Process input file.
	 * 
	 * @param inputFilePath
	 */
	public void processInputFile(final String inputFilePath)
	{
		BufferedReader bufferReader = null;
		try
		{
			if (inputFilePath.toLowerCase().endsWith(".txt"))
			{
				bufferReader = new BufferedReader(new FileReader(inputFilePath));
				System.out.println("Reading Input Text File.....");
				String line;
				while ((line = bufferReader.readLine()) != null)
				{
					// process the line.
					validateLine.validate(line);
				}
			}
			else
				msg.printMessage(Codes.INVALID_FILE);
		}
		catch (Exception exp)
		{
			msg.printMessage(Codes.INVALID_FILE);
		}
	}

	/**
	 * Write output on console and specified file
	 * 
	 * @param outputFilePath
	 */
	public void processOutputFile(final String outputFilePath)
	{
		System.out.println("Writing Output......");

		File file = new File(outputFilePath);
		FileWriter fw = null;
		try
		{
			if (!file.exists())
			{
				file.createNewFile();
			}

			fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bufferWriter = new BufferedWriter(fw);
			ArrayList<String> outputArrayList = validateLine.getOutputList();
			int size = outputArrayList.size();
			for (int i = 0; i < size; i++)
			{
				System.out.println(outputArrayList.get(i));
				bufferWriter.write(outputArrayList.get(i) + "\n");
			}
			bufferWriter.close();
		}
		catch (IOException e)
		{
			msg.printMessage(Codes.INVALID_FILE);
			ArrayList<String> outputArrayList = validateLine.getOutputList();
			int size = outputArrayList.size();
			for (int i = 0; i < size; i++)
			{
				System.out.println(outputArrayList.get(i));
			}
		}
	}

	/**
	 * Process input and output files
	 * 
	 * @param args
	 */
	private void process(String[] args)
	{
		if (args.length == 2)
		{
			processInputFile(args[0]);
			processOutputFile(args[1]);
		}
		else
			msg.printMessage(Codes.INVALID_FILE);
	}

	/**
	 * starts towards Merchant's Guide to the Galaxy..!!
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		System.out.println("Welcome to Merchant's Guide to the Galaxy..!!");

		GalaxyGuide gg = new GalaxyGuide();
//		gg.processInputFile("C:\\Users\\gautam_joshi02\\workspace\\assignment_workspace\\MerchantGalaxyGuide\\test_data\\input\\input_test_data_1.txt");

//		gg.processOutputFile("C:\\Users\\gautam_joshi02\\workspace\\assignment_workspace\\MerchantGalaxyGuide\\test_data\\output\\output_test_data_1.txt");
		gg.process(args);
	}
}