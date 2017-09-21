# Sudoku Downloader

This is an easily extendable program that can be used to download and parse sudoku puzzles from various sources.
Currently implemented are download from the [archive of http://sudokuoftheday.co.uk/](sudokuoftheday.co.uk). 
Parsing exists for that format and the [1 million sudokus dataset from Kaggle](https://www.kaggle.com/bryanpark/sudoku). 
Output is in the format that is used as input by [this sudoku-to-sat-encoder](https://github.com/e-wipond/sudoku-to-SAT).

Other formats can easily be included by creating a new class implementing the downloader/filereader/parser/encoder interface.

The workflow goes like this:  
   1. The downloader downloads a specified number of files and writes them to a specified directory. It is a concrete downloader's responsibility to know about the structure of the website and how to get the desired number of files, where one file may contain multiple sudokus.
   2. The filereader goes through the files downloaded by the downloader. It collects all lines containing valid sudokus in a list (a concrete fileReader must implement the isValid method for this) and passes them to a consumer one by one.
   3. The parser consumes lines from the fileReader and knows how to extract the sudoku (and possibly metadata) from that. The sudoku is now converted to an internal Grid object.
   4. The encoder is responsible for writing the sudoku to a file in the desired format. That might be the DIMACS format commonly used by SAT-solvers or anything else.     
