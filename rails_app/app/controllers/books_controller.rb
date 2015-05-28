class BooksController < ApplicationController
  # GET /books
  # GET /books.json
  def index
    @books = Book.all
    render json: @books.to_json
  end

  # POST /books
  # POST /books.json
  def create
    @book = Book.new(book_params)
    @book.save

    redirect_to books_path
  end

  # DELETE /books/all
  # DELETE /books/all.json
  def all
    Book.delete_all
    redirect_to books_path
  end

private

  # Never trust parameters from the scary internet, only allow the white list through.
  def book_params
    params.require(:book).permit(:title, :authors)
  end
end
