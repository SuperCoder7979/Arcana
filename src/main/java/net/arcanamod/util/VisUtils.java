package net.arcanamod.util;

import net.arcanamod.aspects.AspectStack;
import net.arcanamod.aspects.Aspects;
import net.arcanamod.aspects.IAspectHandler;
import net.arcanamod.aspects.IAspectHolder;

// TODO: this stuff should be fine in AspectHandler but that's currently a little messy so its here rn
public final class VisUtils{
	
	/**
	 * Moves som eor all aspects from <code>from</code> to <code>to</code>.
	 * Iterates through every holder in <code>from</code> and tries to empty them into every holder in <code>to</code>.
	 * If max is set to -1, up to all aspects will be moved. Otherwise, only up to max aspects will be moved.
	 *
	 * @param from
	 * 		The aspect handler to draw aspects from.
	 * @param to
	 * 		The aspect handler to insert into.
	 * @param max
	 *      The maximum amount of aspects to move, or -1 for no limit.
	 */
	public static void moveAllAspects(IAspectHandler from, IAspectHandler to, int max){
		int transferred = 0;
		for(IAspectHolder holder : from.getHolders()){
			if(transferred >= max && max != -1)
				break;
			if(holder.getCurrentVis() > 0)
				for(IAspectHolder toHolder : to.getHolders()){
					if(transferred >= max && max != -1)
						break;
					if(toHolder.getContainedAspect() == holder.getContainedAspect() || toHolder.getContainedAspect() == Aspects.EMPTY)
						if(toHolder.getCapacity() > toHolder.getCurrentVis()){
							int toInsert = Math.min(holder.getCurrentVis(), toHolder.getCapacity() - toHolder.getCurrentVis());
							if(max != -1)
								toInsert = Math.min(toInsert, max - transferred);
							AspectStack stack = new AspectStack(holder.getContainedAspect(), toInsert);
							transferred += holder.drain(stack, false);
							toHolder.insert(stack, false);
						}
				}
		}
	}
	
	/**
	 * Moves all aspects from <code>from</code> to <code>to</code>.
	 * Iterates through every holder in <code>from</code> and tries to empty them into every holder in <code>to</code>.
	 *
	 * @param from
	 * 		The aspect holder to draw aspects from.
	 * @param to
	 * 		The aspect handler to insert into.
	 */
	public static void moveAspects(IAspectHolder from, IAspectHandler to){
		if(from.getCurrentVis() > 0)
			for(IAspectHolder toHolder : to.getHolders())
				if(toHolder.getContainedAspect() == from.getContainedAspect() || toHolder.getContainedAspect() == Aspects.EMPTY)
					if(toHolder.getCapacity() > toHolder.getCurrentVis()){
						int toInsert = Math.min(from.getCurrentVis(), toHolder.getCapacity() - toHolder.getCurrentVis());
						from.drain(new AspectStack(from.getContainedAspect(), toInsert), false);
						toHolder.insert(new AspectStack(from.getContainedAspect(), toInsert), false);
					}
	}
}