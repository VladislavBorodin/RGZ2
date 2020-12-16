class CreateBankomats < ActiveRecord::Migration[5.2]
  def change
    create_table :bankomats do |t|
      t.string :number
      t.string :password
      t.integer :balance
      t.string :name

      t.timestamps
    end
  end
end
